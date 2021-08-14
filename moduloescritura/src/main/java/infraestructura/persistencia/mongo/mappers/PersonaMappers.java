package infraestructura.persistencia.mongo.mappers;

import dominio.entidades.Persona;
import infraestructura.persistencia.mongo.model.PersonaModel;

import javax.inject.Singleton;

@Singleton
public class PersonaMappers {
    public PersonaModel personaToPersonaModel(Persona persona){
        return new PersonaModel(
                persona.getDna(),
                persona.getCantidadDeSecuenciasNitrogenadas(),
                persona.getEsMutante()
        );
    }
    public Persona personaToPersonaModel(PersonaModel personaModel){
        return new Persona(
                personaModel.getDna(),
                personaModel.getCantidadDeSecuenciasNitrogenadas(),
                personaModel.getEsMutante()
        );
    }
}
