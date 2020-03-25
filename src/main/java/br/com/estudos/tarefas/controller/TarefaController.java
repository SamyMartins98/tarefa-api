package br.com.estudos.tarefas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.estudos.tarefas.model.Tarefa;
import br.com.estudos.tarefas.report.GerarRelatorioTarefa;
import br.com.estudos.tarefas.repository.filters.TarefaFiltro;
import br.com.estudos.tarefas.service.TarefaService;
import br.com.estudos.tarefas.vo.TotaisTarefaVo;

@RestController
@RequestMapping(value="/tarefas")
public class TarefaController {

	TarefaService tarefaService;
	GerarRelatorioTarefa gerarRelatorioTarefa;
	
	@Autowired
	public TarefaController(TarefaService tarefaService, GerarRelatorioTarefa gerarRelatorioTarefa) {
		this.tarefaService = tarefaService;
		this.gerarRelatorioTarefa = gerarRelatorioTarefa;
	}
	
	@PostMapping("cadastrar")
	public ResponseEntity<Tarefa> cadastrarTarefa(@RequestBody Tarefa tarefas){
		return ResponseEntity.status(HttpStatus.CREATED).body(tarefaService.salvarTarefa(tarefas));
	}
	
	@GetMapping
	public ResponseEntity<List<Tarefa>> findAll(){
		return ResponseEntity.ok().body(tarefaService.listarTarefas());
	}
	
	@GetMapping("/{tarefaId}")
	public ResponseEntity<Optional<Tarefa>> findById(@PathVariable Long tarefaId){
		return ResponseEntity.ok().body(tarefaService.listarTarefaPorId(tarefaId));
	}
	
	@PutMapping("/{tarefaId}")
	public ResponseEntity<Tarefa> update(@PathVariable Long tarefaId, @RequestBody Tarefa tarefa){
		return ResponseEntity.ok().body(tarefaService.atualizarTarefa(tarefaId, tarefa));
	}
	
	
	@DeleteMapping("/{tarefaId}")
	public ResponseEntity<?> delete(@PathVariable Long tarefaId) {
		tarefaService.deletarTarefa(tarefaId);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/filtro")
	public ResponseEntity<Page<Tarefa>> findAllByFilter(TarefaFiltro filtro, Pageable pageable){
		return ResponseEntity.ok().body(tarefaService.findAllByFiltro(filtro, pageable));
	}
	
	@GetMapping("/relatorio/pdf")
	public ResponseEntity<byte[]> gerarRelatorioPDF(TarefaFiltro filtro){
		
		List<Tarefa> tarefas = tarefaService.getDadosRelatorio(filtro);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(gerarRelatorioTarefa.toPDF(tarefas));
	}
	
	@GetMapping("/relatorio/xls")
	public ResponseEntity<byte[]> gerarRelatorioXLS(TarefaFiltro filtro) {
		
		List<Tarefa> faturas = tarefaService.getDadosRelatorio(filtro);
		String fileName="relatorio_tarefa.xls";
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_OCTET_STREAM)
	            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + "\"")
				.body(gerarRelatorioTarefa.toXLS(faturas));
	}
	
	@GetMapping("/totais")
	public ResponseEntity<TotaisTarefaVo> findTotaisTarefas() {
		return ResponseEntity.ok().body(tarefaService.findTotaisTarefas());
	}
}
