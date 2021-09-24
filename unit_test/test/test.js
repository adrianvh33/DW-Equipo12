var expect = require('chai').expect
var User = require('../userModel');

describe('#userModel test their validations', function(){

    it('Should be invalid if name is empty', function( done ){
      var user1 = new User({
        id_usuario: 1234567890,
        correo:  "correo1@correo.com",
        apellido: "Perez",
        password: "password",
        telefono: 3115555555,
        carrera: "Ingeniería de sistemas",
        id_proyectos: [1,2],
        rol:"estudiante",
        fecha_ingreso: new Date() 
      })
  
      user1.validate(function(err){
        expect( err.errors.nombre ).to.exist;
        done();
      })
    })  
    
  })