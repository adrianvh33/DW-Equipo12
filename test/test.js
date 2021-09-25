var assert = require('assert');
var expect = require('chai').expect

var Proyecto = require('../src/models/proyectoModel');
var User = require('../src/models/user.model');

describe('#userModel and proyect test their validations', function(){

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

  it('Should be invalid if name is empty', function( done ){
    var proyecto1 = new Proyecto({
      id_proyecto: 2,
  
  integrantes:[
      {"id_usuario":1144000000, "horas":4},
      {"id_usuario":1234567890, "horas":4},
      ],
  director: 49632110,
  presupuesto: 1000000,
  descripcion : "Se diseñara e implementara un dispositivo capas detectar la diabetes",
  objectivos:[
      {
          "general": "Desarrollar un dispositivo capaz de dectar la diabetes de manera no invasiva",
          "espesificos": {
              "primero": "Establecer el estado del arte",
              "segundo": "Diseñar el  dispositivo",
              "tercero": "implementar el dispositivo",
              "cuarto" : "validar el dispositivo"
          }
      }],
  estado: "inicio",
  fechas:[
      {
          "fecha_inicio": new Date(),
          "fecha_final":  new Date()
  }],
  fase:[
      {
          "inicio":"fase_iniciada", 
          "avances":
                  [
              {"id_avance":1,  "fecha_avance":new Date(), "avance": "Proyecto creado" },
              {"id_avance":2,  "fecha_avance":new Date(), "avance": "Asignación de estudiante" },
                  ]
      },

      {
          "desarrollo": "fase_no_iniciada", 
          "avances":[]},
      {
          "documentacion": "fase_no_iniciada", 
          "avances":[]},
      {
          "entrega": "fase_no_iniciada", 
          "avances":[]},
      {
          "finalizacion": "fase_no_iniciada", 
          "avances":[]},
  ],
  notas_desempeno:[
      {"fecha_nota":new Date(), "nota":"El proyecto inicio correctamente"}
      ]
    })

    proyecto1.validate(function(err){
      expect( err.errors.nombre ).to.exist;
      done();
    })
  }) 




})