package co.udea.ssmu.api.service.user;

import co.udea.ssmu.api.model.dto.Usuario;
import co.udea.ssmu.api.model.entity.UsuarioEntity;

import co.udea.ssmu.api.model.mapper.UsuarioMapper;

import co.udea.ssmu.api.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{


    private final UserRepository userRepository;
    private final UsuarioMapper usuarioMapper;


    // Constructor for constructor injection
    @Autowired
    public UserServiceImpl(UsuarioMapper usuarioMapper, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.usuarioMapper = usuarioMapper;
    }



    //Actualizar el estado de un usuario
    @Override
    public Usuario estadoUser(int idUser, Boolean estado) {
        Optional<UsuarioEntity> userResult  = userRepository.findById(idUser);

        //Verifico que exista el usuario
        if( ! userResult.isPresent() ) {
            return null;
        }

        //Obtengo el resultado y actualizo valores
        UsuarioEntity usuarioE = userResult.get();
        usuarioE.setHabilitado(estado);
        userRepository.save(usuarioE);


        return usuarioMapper.toDto(usuarioE);
    }
}
