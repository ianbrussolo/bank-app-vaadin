package com.bankapp.application.views;

import com.bankapp.application.data.entity.Account;
import com.bankapp.application.data.entity.Transaction;
import com.bankapp.application.data.entity.User;
import com.bankapp.application.data.service.AccountService;
import com.bankapp.application.data.service.TransactionService;
import com.bankapp.application.data.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.applayout.AppLayout;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;

import javax.swing.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

@PageTitle("Home")
@Route(value = "home")
@CssImport(value = "./css/main.css")
public class HomeView extends VerticalLayout {
//    @Autowired
//    private Authentication authentication;
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TransactionService transactionService;


    User actualUser;
    public HomeView(UserService userService,
                    AccountService accountService,
                    TransactionService transactionService) {
        this.userService = userService;
        this.accountService = accountService;
        this.transactionService = transactionService;

        //gets actual user id
        setWidthFull();
        setMargin(false);
        setClassName("layout");
        //Header
        HorizontalLayout navbar = new HorizontalLayout();
        navbar.setWidthFull();
        navbar.setClassName("navbar");
        Button logo= new Button("K5 bank");
        logo.addClassName("logo-btn");
        Div welcome = new Div();
        welcome.setClassName("welcome");
        Paragraph welcomeText = new Paragraph("Olá, {{nome}}!"/* + actualUser.name*/);
        welcomeText.setClassName("welcome-text");
        Button logoutBtn = new Button("Logout");
        logoutBtn.addClassName("logout-btn");
        welcome.add(welcomeText, logoutBtn);
        navbar.add(logo, welcome);
        add(navbar);

        VerticalLayout body = new VerticalLayout();
        body.setWidth("50%");
        body.setClassName("body");

        //Fazer transferencia
        Div transactionBody = new Div();
        Accordion transferAccordion = new Accordion();
        transferAccordion.setClassName("transfer-accordion");
        FormLayout transferForm = new FormLayout();
        transferForm.addClassName("transfer-card");

        NumberField accountNumberField = new NumberField();
        accountNumberField.setMin(1000);
        accountNumberField.setMax(9999);
        accountNumberField.setLabel("Número da conta");
        accountNumberField.setMaxLength(4);
        Double beneficiaryAccId;
        if (accountNumberField.getValue() != null) {
            beneficiaryAccId = accountNumberField.getValue();
        }

        Select<String> accountMenu = new Select<>();
        accountMenu.setLabel("Selecione a conta de origem");
        accountMenu.setItems("Conta 1", "Conta 2",
                  "Conta 3"/*, actualUser.Account<1>*/);
        accountMenu.setValue("-");
        //Account originAcc = accountMenu.getValue();
        //Long originAccId = accountMenu.getValue(originAcc.getId());

        Label transactionLabel = new Label("Selecione o tipo de transferência");
        Select<String> typeMenu = new Select<>();
        typeMenu.setLabel("Selecione o tipo da transferência");
        typeMenu.setItems("PIX - até R$5000,00", "TED - acima de R$5000,00 e até R$10000,00",
                 "DOC - acima de R$10000,00");
        typeMenu.setValue("-");
        String type = typeMenu.getValue();

        NumberField transferAmountField = new NumberField("Insira o valor a ser transferido");
        transferAmountField.setMin(0.01);
        transferAmountField.setMax(100000);
        transferAmountField.setStep(0.01);
        transferAmountField.setMaxLength(4);
        transferAmountField.setPattern("#.##");
        Div brlPrefix = new Div();
        brlPrefix.setText("R$");
        transferAmountField.setPrefixComponent(brlPrefix);
        transferAmountField.setValueChangeMode(ValueChangeMode.LAZY);
        Double amount;
        if (accountNumberField.getValue() != null) {
            amount = transferAmountField.getValue();
        }

        transferForm.add(accountNumberField);
        transferForm.add(accountMenu);
        transferForm.add(typeMenu, transferAmountField);

        Button transferButton = new Button("Transferir");
        transferButton.setClassName("transfer-btn");
        transferButton.addClickListener(event -> {
            //Transaction transaction = new Transaction();
            Notification.show("Transferência realizada!");
        });
        transferButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        transferForm.add(transferButton);
        transferAccordion.add("Fazer uma transferência", transferForm);
        transactionBody.add(transferAccordion);
        body.add(transactionBody);
        add(body);

        //criar conta
        Div accountsBody = new Div();

        Accordion accountAccordion = new Accordion();
        accountAccordion.setClassName("account-accordion");
        FormLayout accountForm = new FormLayout();
        accountForm.addClassName("account-card");

        //lista as contas criadas pelo usuários
        Grid<Account> grid = new Grid<>(Account.class);
        //if (!accountService.findAllAccounts(actualUser.getUserId()).isEmpty()) {
            //grid.setItems(accountService.findAllAccounts(actualUser.getUserId()));
            accountForm.add(grid);
        //}


        TextField accountNameField = new TextField();
        accountNameField.setLabel("Insira o nome da nova conta");
        String accName = accountNameField.getValue();
        accountForm.add(accountNameField);



        Button accConfirmButton = new Button("Criar conta");
        accConfirmButton.setClassName("account-creation-btn");
        accConfirmButton.addClickListener(event -> {
            Notification.show("Conta criada!");
        });
        accConfirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        accountForm.add(accConfirmButton);
        accountAccordion.add("Minhas contas", accountForm);
        accountsBody.add(accountAccordion);
        body.add(accountsBody);
        add(body);

    }

}
