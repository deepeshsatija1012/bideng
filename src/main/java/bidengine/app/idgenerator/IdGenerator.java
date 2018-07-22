package bidengine.app.idgenerator;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
	private static final ConcurrentMap<Class<?>, AtomicLong> 
			ID_MAP = new ConcurrentHashMap<>();
	
	public static String getId(Class<?> clazz) {
		AtomicLong aLong = ID_MAP.putIfAbsent(clazz, new AtomicLong(1000));
		if(aLong==null) {
			aLong = ID_MAP.get(clazz);
		}
		long id = aLong.getAndIncrement();
		return id+"_"+id;
	}

}
