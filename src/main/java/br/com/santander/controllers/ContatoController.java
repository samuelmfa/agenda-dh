package br.com.santander.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.santander.model.Contato;
import br.com.santander.service.ContatoService;

@RestController
@RequestMapping(value = "/contato")
public class ContatoController {
	
	 @Autowired
	    private ContatoService service;

	    @GetMapping("/{id}")
	    public ResponseEntity<Contato> buscar(@PathVariable("id") Long id) {
	        Contato obj = service.find(id);
	        return ResponseEntity.ok().body(obj);

	    }
	    
	    @GetMapping()
	    public ResponseEntity<List<Contato>> buscarTudo() {
	       List<Contato> obj = service.findAll();
	        return ResponseEntity.ok().body(obj);
	    }	   

	    @PostMapping()
	    public ResponseEntity<Void> insert(@RequestBody Contato obj) {
	    	
	        obj = service.insert(obj);
	        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	        return ResponseEntity.created(uri).build();
	    }


	    /* @PutMapping(value = "/{id}")
	    public ResponseEntity<Void> update(@Valid @RequestBody ContatoDTO objDto, @PathVariable("id") Integer id) {
	        Contato obj = service.fromDTO(objDto);
	        obj.setId(id);
	        obj = service.update(obj);
	        return ResponseEntity.noContent().build();

	    }

	    @DeleteMapping("/{id}")
	    @PreAuthorize("hasAnyRole('ADMIN')")
	    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id) {
	        service.delete(id);
	        return ResponseEntity.noContent().build();
	    }

	    @PostMapping("/picture")
	    public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file) {
	        URI uri = service.uploadProfilePicture(file);
	        return ResponseEntity.created(uri).build();
	    }*/

}
