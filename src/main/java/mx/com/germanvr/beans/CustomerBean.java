package mx.com.germanvr.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Autor Luis German Vazquez Renteria
 * @Proyecto: https://github.com/GermanVR/
 * @Correo: german_1241@hotmail.com
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBean {

	private long id;
	private String name;
	private int age;

}
