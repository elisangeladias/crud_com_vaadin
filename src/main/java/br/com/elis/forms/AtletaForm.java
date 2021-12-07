/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elis.forms;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import java.util.Locale;
import br.com.elis.ConfirmationDialog;
import br.com.elis.MainView;
import br.com.elis.model.Atleta;
import br.com.elis.model.AtletaBase;
import br.com.elis.model.AtletaPrancha;
import br.com.elis.model.AtletaStatus;
import com.vaadin.flow.component.textfield.EmailField;
import br.com.elis.model.AtletaRepository;


/**
 *
 * @author elis
 */
public class AtletaForm extends FormLayout {
    private TextField primeiroNome = new TextField("Primeiro nome");
    private TextField sobrenome = new TextField("Sobrenome");
    private ComboBox<AtletaStatus> status = new ComboBox<>("Status");
    private ComboBox<AtletaBase> base = new ComboBox<>("Base");
    private ComboBox<AtletaPrancha> prancha = new ComboBox<>("Prancha");
    private DatePicker dataNascimento = new DatePicker("Data de nascimento");
    private EmailField email = new EmailField("E-mail");
    private Button salvarButton = new Button("Salvar");
    private Button excluirButton = new Button("Excluir");
    
    private MainView mainView;
    private ConfirmationDialog confirmationDialog = new ConfirmationDialog();
    
    private Binder<Atleta> binder = new Binder<>(Atleta.class);
    private AtletaRepository repository;
        
    public AtletaForm(MainView mainView, AtletaRepository repository) {
        this.mainView = mainView;
        this.repository = repository;
        binder.bindInstanceFields(this);
        status.setItems(AtletaStatus.values());
        base.setItems(AtletaBase.values());
        prancha.setItems(AtletaPrancha.values());
        dataNascimento.setLocale(new Locale("pt", "BR"));

        HorizontalLayout btns = new HorizontalLayout(salvarButton, excluirButton);
        salvarButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        salvarButton.addClickListener(event -> salvar());
        excluirButton.addClickListener(event -> excluir());
        
        add(primeiroNome, sobrenome, email, status, base, prancha, dataNascimento, btns);
    }
    
    public void setAtleta(Atleta atleta) {
        binder.setBean(atleta);

        if (atleta == null) {
            setVisible(false);
        } else {
            setVisible(true);
            if (binder.getBean().isPersisted()) {
                excluirButton.setVisible(true);
            } else {
                excluirButton.setVisible(false);
            }
            primeiroNome.focus();
        }
    }

    private void salvar() {
        Atleta atleta = binder.getBean();
        repository.save(atleta);
        mainView.atualizarLista();
        setAtleta(null);
    }
    
    private void excluir() {
        Atleta atleta = binder.getBean();
        confirmationDialog.setQuestion("Deseja realmente excluir a/o atleta '"+ atleta.toString() + "'?");
        confirmationDialog.open();
        confirmationDialog.addConfirmationListener(evt -> {
            repository.delete(atleta);
            mainView.atualizarLista();
            setAtleta(null);
        });
    }
    
}
