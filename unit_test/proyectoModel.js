const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const proyetoSchema = new Schema({
    id_proyecto: { type: Number, required: true },
    nombre: { type: String, required: true },
    integrantes:{ type: Array, required: true },
    director: { type: Number, required: true },
    presupuesto: { type: Number, required: true },
    descripcion : { type: String, required: true },
    objectivos:{ type: Array, required: true },
    estado: { type: String, required: true },
    fechas:{ type: Array, required: true },
    fase:{ type: Array, required: true },
    notas_desempeno:{ type: Array, required: true }
});

const Proyecto = mongoose.model('Proyecto', proyetoSchema)
module.exports = Proyecto