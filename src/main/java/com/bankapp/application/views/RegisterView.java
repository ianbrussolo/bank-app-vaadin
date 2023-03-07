package com.bankapp.application.views;

//import com.bankapp.application.views.MainLayout;

import com.bankapp.application.data.entity.User;
import com.bankapp.application.data.repository.UserRepository;
import com.bankapp.application.data.service.UserService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@PageTitle("Register")
@Route(value = "register"/*, layout = MainLayout.class*/)
@AnonymousAllowed
@Uses(Icon.class)
public class RegisterView extends VerticalLayout {

    private User newUser;
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public RegisterView() {
        // set background image
        Image backgroundImage = new Image("images/background.png", "background image");
        backgroundImage.setSizeFull();
        backgroundImage.setWidth("1924px");
        backgroundImage.setHeight("977px");
        backgroundImage.getStyle().set("margin-top", "-20px");
        backgroundImage.getStyle().set("margin-bottom", "-20px");
        backgroundImage.getStyle().set("margin-right", "-20px");
        backgroundImage.getStyle().set("margin-left", "-20px");
        add(backgroundImage);

        H2 title = new H2("Registre-se em nosso site:");
        Div mainCard = new Div();
        // Create card body
        Div cardBody = new Div();
        cardBody.getStyle().set("padding", "20px");
        cardBody.getStyle().set("background-color", "white");

        // create form
        FormLayout formLayout = new FormLayout();
        TextField nameField = new TextField("Nome");
        EmailField emailField = new EmailField("Email");
        PasswordField passwordField = new PasswordField("Senha");
        passwordField.setHelperText(
                "A senha deve conter 8 dígitos, caracteres especiais e letras maiúsculas e minúsculas.");
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).*$";
        passwordField.setPattern(passwordPattern);
        Pattern pattern = Pattern.compile(passwordPattern);
        Matcher matcher = pattern.matcher(passwordField.getValue());
        passwordField.setErrorMessage("Não é uma senha válida");

        //buttons
        Div buttonWrapper = new Div();
        buttonWrapper.getStyle().set("display", "flex");
        buttonWrapper.getStyle().set("justify-content", "center");
        buttonWrapper.getStyle().set("align-items", "center");
        buttonWrapper.getStyle().set("marginTop", "30px");
        buttonWrapper.getStyle().set("gap", "20px");
        // Create register button
        Button registerBtn = new Button("Registrar");
        registerBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerBtn.addClickListener(e -> {
            newUser = new User();
            Binder<User> binder = new Binder<>();
            binder.forField(nameField)
                    .asRequired("Por favor forneça um nome").bind(User::getName, User::setName);
            binder.forField(emailField)
                    .withValidator(new EmailValidator("Email inválido")).bind(User::getEmail, User::setEmail);
            binder.forField(passwordField)
                    .bind(User::getPassword, User::setPassword);
            if(binder.validate().isOk()) {
                newUser = new User(nameField.getValue(), emailField.getValue(), passwordField.getValue());
                userRepository.save(newUser);
                UI.getCurrent().navigate(LoginView.class);
            }
        });
        // Create login button
        Button loginBtn = new Button("Cancelar");
        loginBtn.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        loginBtn.addClickListener(e -> {
            UI.getCurrent().navigate(WelcomeView.class);
        });
        // Add buttons to button wrapper
        buttonWrapper.add(registerBtn, loginBtn);

        // set form layout and alignment
        setFlexGrow(1, formLayout);
        setHorizontalComponentAlignment(Alignment.CENTER, formLayout);
        formLayout.add(nameField, emailField, passwordField);
        cardBody.add(title, formLayout, buttonWrapper);
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

    public List<User> getUserList() {
        return userList;
    }

}
