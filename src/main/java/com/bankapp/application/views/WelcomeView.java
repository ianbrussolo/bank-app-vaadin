package com.bankapp.application.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Welcome")
@Route(value = ""/*, layout = MainLayout.class*/)
@AnonymousAllowed
@CssImport(value = "./css/default.css")
public class WelcomeView extends VerticalLayout {

    public WelcomeView() {
        // Set background image
        Image backgroundImage = new Image("images/background.png", "background image");
        backgroundImage.setWidth("1924px");
        backgroundImage.setHeight("977px");
        backgroundImage.getStyle().set("margin-top", "-20px");
        backgroundImage.getStyle().set("margin-bottom", "-20px");
        backgroundImage.getStyle().set("margin-right", "-20px");
        backgroundImage.getStyle().set("margin-left", "-20px");
        add(backgroundImage);

        // Create main card and center it
        Div mainCard = new Div();
        mainCard.setWidth("50%");
        mainCard.setHeight("40%");

        // Create card body
        Div cardBody = new Div();
        cardBody.getStyle().set("padding", "20px");

        // Create title
        H1 title = new H1("K5 bank");
        title.getStyle().set("font-weight", "bold");
        title.getStyle().set("font-size", "50px");
        title.getStyle().set("font-family", "Tilt Warp");
        title.getStyle().set("marginTop", "10px");

        // Create subtitle
        H3 subtitle = new H3("Seu banco online");
        subtitle.getStyle().set("marginBottom", "20px");

        // Create text
        Paragraph text = new Paragraph(
                "K5 bank é um portal virtual que te permite gerenciar suas contas bancárias de forma online. Nosso site é uma alternativa prática e conveniente para quem não tem tempo de ir até uma agência bancária, além de oferecer maior segurança para as transações financeiras. É possível acessar o site a qualquer momento do dia, em qualquer lugar com acesso à internet, facilitando a vida de quem tem uma rotina agitada."
        );

        // Create button wrapper
        Div buttonWrapper = new Div();
        buttonWrapper.getStyle().set("display", "flex");
        buttonWrapper.getStyle().set("marginTop", "30px");
        buttonWrapper.getStyle().set("gap", "20px");

        // Create register button
        Button registerButton = new Button("Registrar");
        registerButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        registerButton.addClickListener(e -> {
            UI.getCurrent().navigate(RegisterView.class);
        });

        // Create login button
        Button loginButton = new Button("Login");
        loginButton.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        loginButton.addClickListener(e -> {
            UI.getCurrent().navigate(LoginView.class);
        });
        loginButton.getStyle().set("background-color", "#391b9e");
        loginButton.getStyle().set("color", "white");

        // Add buttons to button wrapper
        buttonWrapper.add(registerButton, loginButton);

        // Add components to card body
        cardBody.add(title, subtitle, text, buttonWrapper);

        // Add card body to main card
        mainCard.add(cardBody);

        // Add main card to background image
        add(mainCard);

        //styling and positioning
        getStyle().set("position", "relative");
        getStyle().set("overflow", "hidden");
        getStyle().set("display", "flex");
        getStyle().set("justify-content", "center");
        getStyle().set("align-items", "center");

        mainCard.getStyle().set("margin", "auto");
        mainCard.getStyle().set("position", "absolute");
        mainCard.getStyle().set("left", "200px");
        mainCard.getStyle().set("padding", "20px");
        mainCard.getStyle().set("background-color", "white");
        mainCard.getStyle().set("box-shadow", "0px 0px 10px rgba(0, 0, 0, 0.3)");
    }

}
