package domainservices;

import org.junit.rules.ExternalResource;
import org.mockito.Mockito;

public class ServiceMocker extends ExternalResource {

	private RandomHorsesBreeder originalRandomHorsesBreeder;
	
	public static ServiceMocker create() {
		return new ServiceMocker();
	}
	
	@Override
	protected void before() throws Throwable {
		originalRandomHorsesBreeder = ServiceLocator.randomHorsesBreeder;
	}

	@Override
	protected void after() {
		ServiceLocator.randomHorsesBreeder = originalRandomHorsesBreeder;
	}
	
	public void mockRandomHorseBreeder() {
		ServiceLocator.randomHorsesBreeder = Mockito.mock(RandomHorsesBreeder.class);
	}
	
}
