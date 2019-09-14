/**
 * 
 */
package br.com.fake.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 * Aug 30, 2019
 * 
 */
@Getter
@Setter
@Entity
@Table(name="tb_medicos")
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Medico implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome é obrigatório")
	private String nome;
	
	@NotEmpty(message = "{email.vazio}")
	@Email
	private String email;
	
	@NotBlank(message = "CRM é obrigatório")
	private String crm;
}
