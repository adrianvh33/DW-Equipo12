const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const userSchema = new Schema({
  id_usuario: { type: Number, required: true },
  correo: { type: String, required: true },
  nombre: { type: String, required: true },
  apellido:{ type: String, required: true },
  password: { type: String, required: true },
  telefono: { type: Number, required: true },
  carrera: { type: String, required: true },
  id_proyectos: { type: Array, required: true },
  rol:{ type: String, required: true },
  fecha_ingreso: { type: Date, required: true },
});

const User = mongoose.model('User', userSchema)
module.exports = User
