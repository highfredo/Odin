package es.us.isa.odin.repositories.events;

import java.lang.reflect.Field;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.google.common.base.Strings;

import es.us.isa.odin.domain.Document;
import es.us.isa.odin.utilities.reflection.FieldFilterHasAnnotation;

@NoRepositoryBean
public class CreatedDateMongoEventListener extends AbstractMongoEventListener<Document<?>> {
	
	@Override
	public void onBeforeConvert(final Document<?> source) {
		ReflectionUtils.doWithFields(source.getClass(), 
				new FieldCallback() {				
					@Override
					public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
						ReflectionUtils.makeAccessible(field);
						if(Strings.isNullOrEmpty(source.getId()))
							field.set(source, new DateTime());		
					}
				}, 
				new FieldFilterHasAnnotation(CreatedDate.class)
		);
	}
	
}