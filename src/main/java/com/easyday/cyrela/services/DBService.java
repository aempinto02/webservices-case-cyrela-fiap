package com.easyday.cyrela.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyday.cyrela.domain.AtividadeAgendada;
import com.easyday.cyrela.domain.Cidade;
import com.easyday.cyrela.domain.Cliente;
import com.easyday.cyrela.domain.Endereco;
import com.easyday.cyrela.domain.Estado;
import com.easyday.cyrela.domain.Ocorrencia;
import com.easyday.cyrela.domain.enums.Bandeira;
import com.easyday.cyrela.domain.enums.TipoAtividade;
import com.easyday.cyrela.domain.enums.TipoCliente;
import com.easyday.cyrela.repositories.AtividadeAgendadaRepository;
import com.easyday.cyrela.repositories.CidadeRepository;
import com.easyday.cyrela.repositories.ClienteRepository;
import com.easyday.cyrela.repositories.EnderecoRepository;
import com.easyday.cyrela.repositories.EstadoRepository;
import com.easyday.cyrela.repositories.OcorrenciaRepository;

@Service
public class DBService {
	
	@Autowired
	private AtividadeAgendadaRepository atividadeRepository;
	
	@Autowired
	private OcorrenciaRepository ocorrenciaRepository;
	
	@Autowired
	private EstadoRepository estRepository;
	
	@Autowired
	private CidadeRepository cidRepository;
	
	@Autowired
	private ClienteRepository cliRepository;
	
	@Autowired
	private EnderecoRepository endRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "93281654782", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Pedro Almeida", "pedro@gmail.com", "93281654782", TipoCliente.PESSOAFISICA);
		Cliente cli3 = new Cliente(null, "Miranda Pereira", "miranda@gmail.com", "93281654782", TipoCliente.PESSOAFISICA);
		Cliente cli4 = new Cliente(null, "Portin Ltda", "portin@gmail.com", "56889128000102", TipoCliente.PESSOAJURIDICA);
		Cliente cli5 = new Cliente(null, "Lancaster S.A.", "lancaster@gmail.com", "56889128000102", TipoCliente.PESSOAJURIDICA);
		Cliente cli6 = new Cliente(null, "Ston Org.", "ston@gmail.com", "56889128000102", TipoCliente.PESSOAJURIDICA);
		
		Ocorrencia oco1 = new Ocorrencia(null, cli1, 1, 4, 204, Bandeira.LIVING, "Vazamento em unidade");
		Ocorrencia oco2 = new Ocorrencia(null, cli2, 2, 7, 302, Bandeira.CYRELA, "Rachadura preocupante");
		Ocorrencia oco3 = new Ocorrencia(null, cli3, 4, 8, 404, Bandeira.VIVAZ, "Solicitação de ajuste");
		Ocorrencia oco4 = new Ocorrencia(null, cli4, 3, 9, 82, Bandeira.VIVAZ, "Documentos faltantes");
		Ocorrencia oco5 = new Ocorrencia(null, cli5, 6, 10, 102, Bandeira.LIVING, "Metragem incorreta");
		Ocorrencia oco6 = new Ocorrencia(null, cli6, 8, 12, 91, Bandeira.CYRELA, "Renegociação de dívida");
		Ocorrencia oco7 = new Ocorrencia(null, cli2, 4, 15, 901, Bandeira.CYRELA, "Acabamento em más condições");
		
		cli1.getOcorrencias().addAll(Arrays.asList(oco1));
		cli2.getOcorrencias().addAll(Arrays.asList(oco2, oco7));
		cli3.getOcorrencias().addAll(Arrays.asList(oco3));
		cli4.getOcorrencias().addAll(Arrays.asList(oco4));
		cli5.getOcorrencias().addAll(Arrays.asList(oco5));
		cli6.getOcorrencias().addAll(Arrays.asList(oco6));

		cli1.getTelefones().addAll(Arrays.asList("24158275", "981754162"));
		cli2.getTelefones().addAll(Arrays.asList("55126654", "991431885"));
		cli3.getTelefones().addAll(Arrays.asList("35016354", "954187213"));
		cli4.getTelefones().addAll(Arrays.asList("55199615", "939120649"));
		cli5.getTelefones().addAll(Arrays.asList("40041320", "932817592"));
		cli6.getTelefones().addAll(Arrays.asList("51238812", "974192859"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		AtividadeAgendada at1 = new AtividadeAgendada(null, sdf.parse("21/01/2022"), sdf.parse("30/08/2023"), TipoAtividade.ENCERRAMENTO, "Melhoria final na unidade", 1, 4, 91);
		AtividadeAgendada at2 = new AtividadeAgendada(null, sdf.parse("05/10/2021"), sdf.parse("03/03/2022"), TipoAtividade.VISTORIA, "Conclusão de obra específica", 10, 5, 105);
		AtividadeAgendada at3 = new AtividadeAgendada(null, sdf.parse("08/11/2021"), sdf.parse("19/06/2022"), TipoAtividade.PERICIA, "Revestimento manchado", 4, 8, 54);
		AtividadeAgendada at4 = new AtividadeAgendada(null, sdf.parse("12/04/2022"), sdf.parse("11/07/2023"), TipoAtividade.PERICIA, "Verificação de qualidade", 12, 5, 41);
		AtividadeAgendada at5 = new AtividadeAgendada(null, sdf.parse("09/06/2021"), sdf.parse("14/10/2021"), TipoAtividade.ENCERRAMENTO, "Reforma específica", 41, 19, 60);
		AtividadeAgendada at6 = new AtividadeAgendada(null, sdf.parse("25/09/2021"), sdf.parse("27/04/2022"), TipoAtividade.VISTORIA, "Conclusão de laudo", 33, 7, 101);
		AtividadeAgendada at7 = new AtividadeAgendada(null, sdf.parse("28/08/2021"), sdf.parse("13/03/2022"), TipoAtividade.ENCERRAMENTO, "Entrega ao cliente", 32, 22, 304);
		AtividadeAgendada at8 = new AtividadeAgendada(null, sdf.parse("14/07/2021"), sdf.parse("20/05/2022"), TipoAtividade.VISTORIA, "Acabamento limpo", 4, 17, 205);
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Mato Grosso");
		Estado est4 = new Estado(null, "Alagoas");
		Estado est5 = new Estado(null, "Tocantins");
		
		estRepository.saveAll(Arrays.asList(est1, est2, est3, est4, est5));

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Cuiabá", est3);
		Cidade c5 = new Cidade(null, "Maceió", est4);
		Cidade c6 = new Cidade(null, "Palmas", est5);

		cidRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		est3.getCidades().addAll(Arrays.asList(c4));
		est4.getCidades().addAll(Arrays.asList(c5));
		est5.getCidades().addAll(Arrays.asList(c6));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto303", "Jardim Mole", "30128586", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38421736", cli1, c2);
		Endereco e3 = new Endereco(null, "Rua Lunguina", "84", "Apto111", "Peloti", "20931822", cli2, c3);
		Endereco e4 = new Endereco(null, "Alameda Martins", "409", "Apto214", "Mencunda", "60129938", cli3, c4);
		Endereco e5 = new Endereco(null, "Praça Jovem", "567", "Apto577", "Longancundo", "21980084", cli4, c5);
		Endereco e6 = new Endereco(null, "Avenida Lúmen", "984", "Apto72", "Prantiari", "61298837", cli5, c6);
		Endereco e7 = new Endereco(null, "Rua Dardomélio", "583", "Apto19", "Erfando", "41823328", cli6, c3);
		Endereco e8 = new Endereco(null, "Rua Mergolina", "46", "Apto85", "Regunta", "71826152", cli4, c4);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		cli3.getEnderecos().addAll(Arrays.asList(e4));
		cli4.getEnderecos().addAll(Arrays.asList(e5, e8));
		cli5.getEnderecos().addAll(Arrays.asList(e6));
		cli6.getEnderecos().addAll(Arrays.asList(e7));
		
		cliRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5, cli6));
		ocorrenciaRepository.saveAll(Arrays.asList(oco1, oco2, oco3, oco4, oco5, oco6, oco7));
		atividadeRepository.saveAll(Arrays.asList(at1, at2, at3, at4, at5, at6, at7, at8));
		endRepository.saveAll(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8));
	}
}
