package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.CartaVO;
import br.com.erudio.services.CartaServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin
@Api(tags = "EndpointCarta")
@RestController
@RequestMapping("/api/carta/v1")
public class CartaController {

	@Autowired
	private CartaServices service;

	@ApiOperation(value = "busca todas as cartas")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<PagedResources<CartaVO>> findAll(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "5") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler assembler) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"nome"));
		
		Page<CartaVO> cartas = service.findAll(pageable);
		cartas.stream()
				.forEach(p -> p.add(linkTo(methodOn(CartaController.class).findById(p.getKey())).withSelfRel()));
		return new ResponseEntity<> (assembler.toResource(cartas), HttpStatus.OK);
	}
	
	@ApiOperation(value = "busca todas as cartas filtrando por nome")
	@GetMapping(value = "/findCartaByName/{nome}",produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<PagedResources<CartaVO>> findCartaByName(
			@PathVariable("nome") String nome,
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "5") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler assembler) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"nome"));
		
		Page<CartaVO> cartas = service.findCartaByName(nome, pageable);
		cartas.stream()
		.forEach(p -> p.add(linkTo(methodOn(CartaController.class).findById(p.getKey())).withSelfRel()));
		return new ResponseEntity<> (assembler.toResource(cartas), HttpStatus.OK);
	}
	
	@ApiOperation(value = "busca todas as cartas filtrando por tipo")
	@GetMapping(value = "/findCartaByTipo/{tipo}",produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<PagedResources<CartaVO>> findCartaByTipo(
			@PathVariable("tipo") String tipo,
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "5") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction,
			PagedResourcesAssembler assembler) {
		
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection,"tipo"));
		
		Page<CartaVO> cartas = service.findCartaByTipo(tipo, pageable);
		cartas.stream()
		.forEach(p -> p.add(linkTo(methodOn(CartaController.class).findById(p.getKey())).withSelfRel()));
		return new ResponseEntity<> (assembler.toResource(cartas), HttpStatus.OK);
	}

//	@CrossOrigin(origins = "http://localhost:8080") habilitando o cors
	@ApiOperation(value = "busca cartas por id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public CartaVO findById(@PathVariable("id") Long id) {
		CartaVO cartaVo = service.findById(id);
		cartaVo.add(linkTo(methodOn(CartaController.class).findById(id)).withSelfRel());
		return cartaVo;
	}

	@ApiOperation(value = "cadastrar carta")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public CartaVO create(@RequestBody CartaVO carta) {
		CartaVO cartaVo = service.created(carta);
		cartaVo.add(linkTo(methodOn(CartaController.class).findById(cartaVo.getKey())).withSelfRel());
		return cartaVo;
	}

	@ApiOperation(value = "editar carta")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public CartaVO update(@RequestBody CartaVO carta) {
		CartaVO cartaVo = service.update(carta);
		cartaVo.add(linkTo(methodOn(CartaController.class).findById(cartaVo.getKey())).withSelfRel());
		return cartaVo;
	}

	@ApiOperation(value = "deletar carta")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}