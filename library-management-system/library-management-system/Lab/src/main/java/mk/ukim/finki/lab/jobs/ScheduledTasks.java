package mk.ukim.finki.lab.jobs;

import mk.ukim.finki.lab.service.application.AuthorApplicationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final AuthorApplicationService authorApplicationService;
    public ScheduledTasks(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }
    @Scheduled(cron = "0 0 * * * *")
    public void refreshMaterializedView() {
        this.authorApplicationService.refreshMaterializedView();
    }
}
