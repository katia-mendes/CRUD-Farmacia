package com.generation.crudfarmacia.controller;

	import com.generation.crudfarmacia.model.Categoria;
	import com.generation.crudfarmacia.repository.CategoriaRepository;
	import jakarta.validation.Valid;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.HttpStatus;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.*;
	import org.springframework.web.server.ResponseStatusException;

	import java.util.List;
	import java.util.Optional;

	@RestController
	@RequestMapping("/categoria")
	@CrossOrigin(originPatterns = "*", allowedHeaders = "*")
public class CategoriaController {

	    @Autowired
	    private CategoriaRepository categoriaRepository;

	    @GetMapping("/all")
	    public ResponseEntity<List<Categoria>> getAll() {
	        return ResponseEntity.ok(categoriaRepository.findAll());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Categoria> getById(@PathVariable long id) {
	        return categoriaRepository.findById(id)
	                .map(resp -> ResponseEntity.ok(resp))
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @GetMapping("/nome/{nome}")
	    public ResponseEntity<List<Categoria>> getByNome(@PathVariable String nome) {
	        return ResponseEntity.ok(categoriaRepository.findAllByNomeContainingIgnoreCase(nome));
	    }

	    @PostMapping("/post")
	    public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
	        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
	    }
	    @PutMapping("/put")
	    public ResponseEntity<Categoria> put(@RequestBody Categoria categoria) {
	        return categoriaRepository.findById(categoria.getId())
	                .map(resp -> ResponseEntity.status(HttpStatus.CREATED)
	                        .body(categoriaRepository.save(categoria)))
	                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }

	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    @DeleteMapping("/delete/{id}")
	    public void delete(@PathVariable Long id) {
	        Optional<Categoria> categoria = categoriaRepository.findById(id);
	        if (categoria.isEmpty()) {
	           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada", null);
	        }
	        categoriaRepository.deleteById(id);
	    }
	}


