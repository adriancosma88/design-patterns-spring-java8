package victor.training.oo.structural.facade.facade;

import lombok.RequiredArgsConstructor;
import victor.training.oo.structural.facade.Facade;
import victor.training.oo.structural.facade.entity.Customer;
import victor.training.oo.structural.facade.entity.Email;
import victor.training.oo.structural.facade.facade.dto.CustomerDto;
import victor.training.oo.structural.facade.infra.EmailClient;
import victor.training.oo.structural.facade.repo.EmailRepository;

@Facade
@RequiredArgsConstructor
public class StockFacade {
	private final EmailClient emailClient;
	private final EmailRepository emailRepo;

	public void checkStock() {
		Email email = new Email();
		email.setFrom("noreply");
		email.setTo("admin@myapp.com");
		email.setSubject("Low stock!");
		email.setBody("Buy more!");
		
		if (!emailRepo.emailWasSentBefore(email.hashCode())) {
			emailClient.sendEmail(email.getFrom(), email.getTo(), email.getSubject(), email.getBody());
			emailRepo.saveSentEmail(email);
		}
		
	}
}
