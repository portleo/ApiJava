/**
 *
 * @author Leonardo Portuguez
 * mail l.portuguez91@gmail.com
 * 
 */
package alkemy.challenge.seguridad.controller;

import alkemy.challenge.dto.Mensaje;
import alkemy.challenge.email.EmailSenderService;
import alkemy.challenge.seguridad.dto.JwtDto;
import alkemy.challenge.seguridad.dto.LoginUsuario;
import alkemy.challenge.seguridad.dto.NuevoUsuarioDto;
import alkemy.challenge.seguridad.entity.Rol;
import alkemy.challenge.seguridad.entity.Usuario;
import alkemy.challenge.seguridad.enums.RolNombre;
import alkemy.challenge.seguridad.jwt.JwtProvider;
import alkemy.challenge.seguridad.service.RolService;
import alkemy.challenge.seguridad.service.UsuarioService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired 
    AuthenticationManager authenticationManager;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    RolService rolService;
    
    @Autowired
    JwtProvider jwtProvider;
    
    @Autowired
    EmailSenderService emailSenderService;
    
    @PostMapping("/nuevo")
    public ResponseEntity <?> nuevo (@RequestBody NuevoUsuarioDto nuevoUsuario, BindingResult bindingResult){
     if (bindingResult.hasErrors())
         return new ResponseEntity ( new Mensaje ("camposmal puestos o email invalido"), HttpStatus.BAD_REQUEST);
    if(usuarioService.existByNombreUsuario(nuevoUsuario.getNombreUsuario()))
        return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST );
    if(usuarioService.existByEmail(nuevoUsuario.getEmail()))
        return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST );   
    Usuario usuario = new Usuario ( nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),nuevoUsuario.getEmail(),
    passwordEncoder.encode(nuevoUsuario.getPassword()));
    Set<Rol> roles= new HashSet<>();
    roles.add(rolService.getByNombre(RolNombre.ROL_USER).get());
if (nuevoUsuario.getRoles().contains("admin"))
    roles.add(rolService.getByNombre(RolNombre.ROL_ADMIN).get());
usuario.setRoles(roles);
usuarioService.save(usuario);
emailSenderService.sendSimpleEmail(usuario.getEmail());
    return new ResponseEntity(new Mensaje(" usuario guardado"), HttpStatus.CREATED);
    }
    
   @PostMapping ("/login")
    public ResponseEntity<JwtDto> login (@RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
                 return new ResponseEntity ( new Mensaje ("campos mal puestos"), HttpStatus.BAD_REQUEST);
Authentication  authentication = 
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
SecurityContextHolder.getContext().setAuthentication(authentication);
String jwt = jwtProvider.generateToken(authentication);
UserDetails userDetails = (UserDetails) authentication.getPrincipal();
JwtDto jwtDto= new JwtDto( jwt, userDetails.getUsername(), userDetails.getAuthorities());
 return new ResponseEntity(jwtDto,HttpStatus.OK);
    }
    
}
