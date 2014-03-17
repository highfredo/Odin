package es.us.isa.odin.repositories.events;


import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.ReflectionUtils;

import com.mongodb.DBObject;

import es.us.isa.odin.annotations.PostPersist;
import es.us.isa.odin.annotations.PrePersist;
import es.us.isa.odin.domain.Document;
import es.us.isa.odin.utilities.reflection.MethodCallbackInvoke;
import es.us.isa.odin.utilities.reflection.MethodFilterHasAnnotation;

@NoRepositoryBean
public class PrePostPersistMongoEventListener extends AbstractMongoEventListener<Document<?>> {
	
	@Override
	public void onBeforeConvert(Document<?> source) {		
		ReflectionUtils.doWithMethods(source.getClass(), 
				new MethodCallbackInvoke(source), 
				new MethodFilterHasAnnotation(PrePersist.class)
		);
		
		if(source.getEntity() != null)
			ReflectionUtils.doWithMethods(source.getEntity().getClass(), 
					new MethodCallbackInvoke(source.getEntity()), 
					new MethodFilterHasAnnotation(PrePersist.class)
			);
	}

	@Override
	public void onAfterSave(Document<?> source, DBObject dbo) {
		ReflectionUtils.doWithMethods(source.getClass(), 
				new MethodCallbackInvoke(source), 
				new MethodFilterHasAnnotation(PostPersist.class)
		);	
		
		if(source.getEntity() != null)
			ReflectionUtils.doWithMethods(source.getEntity().getClass(), 
					new MethodCallbackInvoke(source.getEntity()), 
					new MethodFilterHasAnnotation(PostPersist.class)
			);
	}
	
}