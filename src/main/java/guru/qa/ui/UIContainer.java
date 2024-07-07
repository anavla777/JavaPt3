package guru.qa.ui;

import guru.qa.service.Session;

public final class UIContainer implements UIComponent {

    private final UIComponent[] components;

    public UIContainer(UIComponent... components) {
        this.components = components;
    }

    @Override
    public Session render(Session session) {
        Session currentSession = session;
        for (UIComponent component : components) {
            currentSession = component.render(currentSession);
        }
        return currentSession;
    }
}
