package ru.site.application.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.io.ByteArrayInputStream;
import java.util.Optional;
import org.vaadin.lineawesome.LineAwesomeIcon;
import ru.site.application.data.SessionRepository;
import ru.site.application.data.User;
import ru.site.application.security.AuthenticatedUser;
import ru.site.application.views.checkoutform.CheckoutFormView;
import ru.site.application.views.crud.CRUDView;
import ru.site.application.views.method.MethodView;
import ru.site.application.views.theme.ThemeView;
import ru.site.application.views.searcher.SearcherView;
import ru.site.application.views.profile.ProfileView;
import ru.site.application.views.create.CreateTestView;
import ru.site.application.views.test.TestView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H1 viewTitle;

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    private SessionRepository sessionRepository;
    private ThemeView themeView;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker,
                      SessionRepository sessionRepository, ThemeView themeView) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;
        this.sessionRepository = sessionRepository;
        this.themeView = themeView;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Span appName = new Span("site-app");
        appName.addClassNames(LumoUtility.FontWeight.SEMIBOLD, LumoUtility.FontSize.LARGE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());
        Button sessionIcon = themeView.getButtonChangeTheme();

        addToDrawer(header, scroller, createFooter(), sessionIcon);
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        if (accessChecker.hasAccess(MethodView.class)) {
            nav.addItem(new SideNavItem("Методики", MethodView.class, LineAwesomeIcon.TH_LIST_SOLID.create()));

        }
        if (accessChecker.hasAccess(CreateTestView.class)) {
            nav.addItem(new SideNavItem("Создание теста", CreateTestView.class,
                    LineAwesomeIcon.PENCIL_RULER_SOLID.create()));

        }
        if (accessChecker.hasAccess(TestView.class)) {
            nav.addItem(new SideNavItem("Тест", TestView.class, LineAwesomeIcon.PENCIL_RULER_SOLID.create()));

        }
        if (accessChecker.hasAccess(ProfileView.class)) {
            nav.addItem(new SideNavItem("Профиль", ProfileView.class, LineAwesomeIcon.USER.create()));

        }
        if (accessChecker.hasAccess(CheckoutFormView.class)) {
            nav.addItem(new SideNavItem("Checkout Form", CheckoutFormView.class, LineAwesomeIcon.CREDIT_CARD.create()));

        }
        if (accessChecker.hasAccess(CRUDView.class)) {
            nav.addItem(new SideNavItem("CRUD", CRUDView.class, LineAwesomeIcon.COLUMNS_SOLID.create()));

        }
        if (accessChecker.hasAccess(SearcherView.class)) {
            nav.addItem(new SideNavItem("Поисковик", SearcherView.class, LineAwesomeIcon.FILTER_SOLID.create()));

        }

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            Avatar avatar = new Avatar(user.getName());
            StreamResource resource = new StreamResource("profile-pic",
                    () -> new ByteArrayInputStream(user.getProfilePicture()));
            avatar.setImageResource(resource);
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
