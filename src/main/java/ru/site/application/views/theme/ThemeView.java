package ru.site.application.views.theme;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vaadin.lineawesome.LineAwesomeIcon;
import ru.site.application.data.SessionEntity;
import ru.site.application.data.SessionRepository;

@Service
@RequiredArgsConstructor
public class ThemeView {

    private final SessionRepository sessionRepository;

    public Button getButtonChangeTheme() {
        SvgIcon iconTheme;
        var sessionId = VaadinSession.getCurrent().getSession().getId();
        var dbSessionId = sessionRepository.findBySessionId(sessionId);
        if(dbSessionId.isEmpty() == true) {
            SessionEntity sessionEntity = new SessionEntity();
            sessionEntity.setSessionId(sessionId);
            sessionEntity.setTheme("light");
            sessionRepository.save(sessionEntity);
            iconTheme = LineAwesomeIcon.MOON.create();
            UI.getCurrent().getElement().setAttribute("theme", "light");
        } else {
            if (dbSessionId.get().getTheme().equals("dark")) {
                UI.getCurrent().getElement().setAttribute("theme", "dark");
                iconTheme = LineAwesomeIcon.SUN.create();
            } else {
                UI.getCurrent().getElement().setAttribute("theme", "light");
                iconTheme = LineAwesomeIcon.MOON.create();
            }
        }
        Button sessionIcon = new Button(iconTheme);

        sessionIcon.addClickListener(e -> {
            var idSession = sessionRepository.findBySessionId(VaadinSession.getCurrent().getSession().getId());
            if(idSession.get().getTheme().equals("dark")) {
                sessionIcon.setIcon(LineAwesomeIcon.MOON.create());
                UI.getCurrent().getElement().setAttribute("theme", "light");
                SessionEntity sessionEntity = new SessionEntity();
                sessionEntity.setId(idSession.get().getId());
                sessionEntity.setSessionId(idSession.get().getSessionId());
                sessionEntity.setTheme("light");
                sessionRepository.save(sessionEntity);
            } else {
                sessionIcon.setIcon(LineAwesomeIcon.SUN.create());
                UI.getCurrent().getElement().setAttribute("theme", "dark");
                SessionEntity sessionEntity = new SessionEntity();
                sessionEntity.setId(idSession.get().getId());
                sessionEntity.setSessionId(idSession.get().getSessionId());
                sessionEntity.setTheme("dark");
                sessionRepository.save(sessionEntity);
            }
        });
        return sessionIcon;
    }
}
