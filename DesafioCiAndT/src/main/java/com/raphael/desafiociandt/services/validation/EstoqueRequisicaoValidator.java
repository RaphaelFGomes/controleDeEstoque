package com.raphael.desafiociandt.services.validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.raphael.desafiociandt.domain.Historico;
import com.raphael.desafiociandt.dto.EstoqueDTO;
import com.raphael.desafiociandt.exception.FieldMessage;
import com.raphael.desafiociandt.repositories.EstoqueRepositorio;
import com.raphael.desafiociandt.repositories.HistoricoRepositorio;

public class EstoqueRequisicaoValidator implements ConstraintValidator<EstoqueRequisicao, EstoqueDTO> {

	@Autowired
	private EstoqueRepositorio repoEstoque;
	
	@Autowired
	private HistoricoRepositorio repoHistorico;

	@Override
	public void initialize(EstoqueRequisicao ann) {
	}
	
	@Override
	public boolean isValid(EstoqueDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if (objDto.getSecao() == null) {
			list.add(new FieldMessage("secao", "Seção é obrigatória!"));
		} else {
			if (objDto.getSecao() != 1 && objDto.getSecao() != 2 && objDto.getSecao() != 3 && objDto.getSecao() != 4 && objDto.getSecao() != 5) {
				list.add(new FieldMessage("secao", "Seção deve ser os números inteiros 1, 2, 3, 4 ou 5!"));
			} else {
				if (objDto.getBebida() == null) {
					list.add(new FieldMessage("bebida", "Bebida é obrigatório!"));
				} else {
					if (objDto.getBebida().getTipo() == null) {
						list.add(new FieldMessage("bebida", "Tipo da bebida é obrigatório!"));
					} else {
						if (objDto.getBebida().getTipo() != 1 && objDto.getBebida().getTipo() != 2) {
							list.add(new FieldMessage("bebida", "Tipo da bebida deve ser o número inteiro 1 para alcoólico ou 2 para não alcoólico!"));
						} else {
							if (objDto.getBebida().getTipo() == 1 && (objDto.getVolume() <= 0 || objDto.getVolume() > 500)) {
								list.add(new FieldMessage("volume", "O volume deve ser entre o número inteiro 1 e 500 para bebidas alcoólicas!"));
							} else {
								if (objDto.getBebida().getTipo() == 2 && (objDto.getVolume() <= 0 || objDto.getVolume() > 400)) {
									list.add(new FieldMessage("volume", "O volume deve ser entre o número inteiro 1 e 400 para bebidas não alcoólicas!"));
								} else {
									List<Historico> listaHistorico = repoHistorico.findBySecao(objDto.getSecao());
									if (listaHistorico != null) {
										for (Historico hist : listaHistorico) {
											if (hist.getHorarioLocalDate().equals(LocalDate.now()) && hist.getTipoBebida() != objDto.getBebida().getTipo()) {
												list.add(new FieldMessage("secao", "Seção " + objDto.getSecao() + " foi ocupada por outro tipo de bebida hoje, por favor tente no dia seguinte!"));
												break;
											}
										}
									}
									
									Integer totalVolumePorSecao = 0;
									try {
										totalVolumePorSecao = repoEstoque.findVolumeBySecao(objDto.getSecao());
									} catch (Exception e) {
										totalVolumePorSecao = 0;
									}
									
									if (objDto.getBebida().getTipo() == 1 && (totalVolumePorSecao + objDto.getVolume()) > 500) {
										list.add(new FieldMessage("volume", "Não pode armazenar esse volume pois ultrapassa o limite de 500 para bebidas alcoólicas na seção: " + objDto.getSecao() + "!"));
									} else {
										if (objDto.getBebida().getTipo() == 2 && (totalVolumePorSecao + objDto.getVolume()) > 400) {
										list.add(new FieldMessage("volume", "Não pode armazenar esse volume pois ultrapassa o limite de 400 para bebidas não alcoólicas na seção: " + objDto.getSecao() + "!"));
										}
								    }
								}
							}
						}
					}
				}
			}
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}