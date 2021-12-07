package br.com.elis;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.elis.forms.AtletaForm;
import br.com.elis.model.Atleta;
import br.com.elis.model.AtletaRepository;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Surfistas",
     shortName = "Surf-CRUD App",
     description = "Cadastro de atletas",
     enableInstallPrompt = false)
public class MainView extends VerticalLayout {

    private final AtletaRepository repository;
    private Grid<Atleta> grid = new Grid<>(Atleta.class);
    private TextField filterText = new TextField();
    Button novoBtn = new Button("Novo atleta");
    
    private AtletaForm form;
    
    public MainView(@Autowired AtletaRepository repository) {
        this.repository = repository;
        
        // 1
        form = new AtletaForm(this, repository);
        form.setAtleta(null);
        
        // 2
        grid.setColumns("primeiroNome", "sobrenome", "status", "base", "prancha");
        grid.setSizeFull();
        grid.asSingleSelect().addValueChangeListener(e -> selecionar());
        atualizarLista();
        
        // 3
        filterText.setPlaceholder("Filtrar por nome...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.EAGER);
        filterText.addValueChangeListener(e -> atualizarLista());
        
        // 4
        novoBtn.addClickListener(e -> adicionarNovo());
        
        // 5
        HorizontalLayout mainContent = new HorizontalLayout(grid, form);
        mainContent.setSizeFull();
        add(new HorizontalLayout(filterText, novoBtn), mainContent);
        setSizeFull();
    }
    
    public void atualizarLista() {
        if (filterText.getValue().isEmpty()) {
            grid.setItems(repository.findAll());
        } else {
            grid.setItems(repository.findByNome(filterText.getValue()));
        }
    }
    
    public void selecionar() {
        form.setAtleta(grid.asSingleSelect().getValue());
    }

    private void adicionarNovo() {
        grid.asSingleSelect().clear();
        form.setAtleta(new Atleta());
    }

}
