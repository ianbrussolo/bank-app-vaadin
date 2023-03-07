package com.bankapp.application.views;

//import com.bankapp.application.security.AuthenticatedUser;
//import com.bankapp.application.data.service.AuthenticationService;
//import com.bankapp.application.data.service.AuthenticationService;
import com.bankapp.application.data.entity.User;
import com.bankapp.application.data.repository.UserRepository;
import com.bankapp.application.data.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.*;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;

@PageTitle("Login")
@Route("login")
public class LoginView extends VerticalLayout {
    //private final AuthenticationService authService;
    private RegisterView registerView;
    private final EmailField emailField;
    private final PasswordField passwordField;
    //private final UserService userService;

    //private final UserRepository userRepository;
    public LoginView(/*AuthenticationService authService*/) {
        //this.authService = authService;
        this.emailField = new EmailField("Email");
        this.passwordField = new PasswordField("Senha");
        //this.userService = new UserService(userRepository);

        Image backgroundImage = new Image("images/background.png", "background image");
        backgroundImage.setSizeFull();
        backgroundImage.setWidth("1924px");
        backgroundImage.setHeight("977px");
        backgroundImage.getStyle().set("margin-top", "-20px");
        backgroundImage.getStyle().set("margin-bottom", "-20px");
        backgroundImage.getStyle().set("margin-right", "-20px");
        backgroundImage.getStyle().set("margin-left", "-20px");
        add(backgroundImage);

        H2 title = new H2("Faça seu login:");
        Div mainCard = new Div();
        // Create card body
        Div cardBody = new Div();
        cardBody.getStyle().set("padding", "20px");
        cardBody.getStyle().set("background-color", "white");

        // create form
        FormLayout formLayout = new FormLayout();

        //buttons
        Div buttonWrapper = new Div();
        buttonWrapper.getStyle().set("display", "flex");
        buttonWrapper.getStyle().set("justify-content", "center");
        buttonWrapper.getStyle().set("align-items", "center");
        buttonWrapper.getStyle().set("marginTop", "30px");
        buttonWrapper.getStyle().set("gap", "20px");
        // Create login button with event
        Button loginBtn = new Button("Login", e -> validateLogin());
        loginBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Cancel button
        Button cancelBtn = new Button("Cancelar", e -> {
            UI.getCurrent().navigate(WelcomeView.class);
        });
        cancelBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        // Add buttons to button wrapper
        buttonWrapper.add(loginBtn, cancelBtn);

        // set form layout and alignment
        setFlexGrow(1, formLayout);
        setHorizontalComponentAlignment(Alignment.CENTER, formLayout);
        formLayout.add(emailField, passwordField);
        cardBody.add(formLayout, buttonWrapper);
        mainCard.add(cardBody);
        add(mainCard);

        //styling and positioning
        getStyle().set("position", "relative");
        getStyle().set("overflow", "hidden");
        getStyle().set("display", "flex");
        getStyle().set("justify-content", "center");
        getStyle().set("align-items", "center");
        mainCard.getStyle().set("margin", "auto");
        mainCard.getStyle().set("position", "absolute");
        mainCard.getStyle().set("padding", "20px");
        mainCard.getStyle().set("background-color", "white");
        mainCard.getStyle().set("box-shadow", "0px 0px 10px rgba(0, 0, 0, 0.3)");
    }

    private void validateLogin() {
        String email = emailField.getValue();
        String password = passwordField.getValue();
        List<User> userList = registerView.getUserList();
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail() == email &&
                    userList.get(i).getPassword() == password) {
                Notification.show("Usuário autenticado");
                UI.getCurrent().navigate(HomeView.class);
            } else{
                Notification.show("Usuário ou senha inválidos");
            }

        }
    }
}
