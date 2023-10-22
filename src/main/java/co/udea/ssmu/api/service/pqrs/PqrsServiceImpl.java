package co.udea.ssmu.api.service.pqrs;

import co.udea.ssmu.api.model.dto.Pqrs;
import co.udea.ssmu.api.model.entity.PqrsE;
import co.udea.ssmu.api.model.mapper.PqrsMapper;
import co.udea.ssmu.api.model.repository.PqrsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PqrsServiceImpl implements IPqrsService {

    @Autowired
    PqrsRepository pqrsRepository;

    @Autowired
    PqrsMapper pqrsMapper;

    @Override
    public Pqrs createPqrs(Pqrs pqrs) {
        PqrsE pqrsE = pqrsMapper.toEntity(pqrs); // convertir el dto a entidad de base de datos

        pqrsE = pqrsRepository.save(pqrsE); // guardar la entidad en la base de datos

        Pqrs pqrsResponse = pqrsMapper.toDto(pqrsE); // convertir la entidad a dto

        return pqrsResponse;
    }

    @Override
    public Pqrs deletePqrs(Integer id) {
        Optional<PqrsE> pqrsEResult  = pqrsRepository.findByPqrsId(id);

        if( ! pqrsEResult.isPresent() ) {
            return null;
        }

        PqrsE pqrsE = pqrsEResult.get();
        pqrsRepository.delete(pqrsE);

        Pqrs pqrsResponse = pqrsMapper.toDto(pqrsE); // convertir la entidad a dto

        return pqrsResponse;
    }

    @Override
    public Iterable<PqrsE> listPqrs() {
        return pqrsRepository.findAll();
    }

    @Override
    public Pqrs getPqrsById(Integer id) {
        Optional<PqrsE> pqrsEResult  = pqrsRepository.findByPqrsId(id);

        if( ! pqrsEResult.isPresent() ) {
            return null;
        }

        PqrsE pqrsE = pqrsEResult.get();
        Pqrs pqrsResponse = pqrsMapper.toDto(pqrsE); // convertir la entidad a dto

        return pqrsResponse;
    }

    @Override
    public Pqrs updatePqrs(String pqrsState, Integer id) {
        return pqrsMapper.toDto(pqrsRepository.updateStateByPqrsId(pqrsState,id));
    }
}
