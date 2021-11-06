#include <Arduino.h>

int pinsDigitales [12] = {0,1,2,3,4,5,6,7,8,9,10,11};
int sharpDerecha = A4; // pin del sensor de la dercha
int sharpIzquierda  = A2;
int sharpCentro = A3;
int pwmR =255; // valor de la velocidad rapida
int pwmL =110; // valor de la velocidad lento
int pwmLG =110;
int t = 400;  // velocidad lenta
float distanciaCM = 0;
float microsec =0;
int i = 0;
int s;
void setup() {
  // put your setup code here, to run once:
   Serial.begin(9600);
   for (i = 2; i <= 5; i++)
  {
    pinMode (pinsDigitales [i], INPUT); // los pines del 2 al 5 como salidas
  }
  for (i = 6; i <= 11; i++)
  {
    pinMode (pinsDigitales [i], OUTPUT); // los pines del 6 al 11 como entradas
  }
  pinMode (sharpDerecha, INPUT); // Sharp derecho como entrada
  pinMode (sharpIzquierda, INPUT); // Sharp izquierdo como entrada
  for (i = 1; i <=11 ; i++)
  {
    //Serial.println(pinsDigitales[i]); // los pines del 6 al 11 como entradas
  }
}

int sensorSharpDerecho () {
  double senal;
  float vr;
  float mimD = 0.8;  
  float masD = 5;
  senal = analogRead(sharpDerecha);  // pone el valor de la variable senal igual a lo que se lee en pin A2
  vr = (senal * 5 / 1023); // combierte los bits en voltaje
  //Serial.println("vr shapr derecho: ");
  //Serial.println(vr);
  if (vr <= masD && vr >= mimD )   // si el valor real es menor o igual a la distacia minima y mayor que el voltaje minimo
  {
    return 1; // retorna 1 si detecta algo
  }
  else
  
  {
    return 0;
  }
}

int sensorSharpCentro () {
  double senal;
  float vr;
  float mimC = 0.8;  
  float masC = 5;
  senal = analogRead(sharpCentro);  // pone el valor de la variable senal igual a lo que se lee en pin A2
  vr = (senal * 5 / 1023); // combierte los bits en voltaje
  // Serial.println("vr shapr centro: ");
  // Serial.println(vr);
  if (vr <= masC && vr >= mimC )   // si el valor real es menor o igual a la distacia minima y mayor que el voltaje minimo
  {
    return 1; // retorna 1 si detecta algo
  }
  else
  
  {
    return 0;
  }
}

int sensorSharpIzquierdo () {
  double senal;
  float vr;
  float mim = 0.8;  // voltaje minimo que da el sensor sharp
  float mas = 5.0;
  senal = analogRead(sharpIzquierda);  // pone el valor de la variable senal igual a lo que se lee en pin A3
  vr = (senal * 5 / 1023); // combierte los bits en voltaje
  //Serial.println("vr shapr izquiedo: ");
  //Serial.println(vr);
  if (vr <= mas && vr >= mim )   // si el valor real es menor o igual a la distacia minima y mayor que el voltaje minimo
  {
    return 1;  // retorna 1 si detecta algo
  }
  else
  {
    return 0;
  }
}

int sensorLineaAdelanteDerecho () {
  int in;   // variable para la entra del sensor de linea
  in = digitalRead(2); // asigna in con el valor que da el sensor
  if (in == 1) {
    return 1; // si el sensor dectecta la linea negra restorna 1
  }
  else {
    return 0;
  }
}


int  sensorLineaAdelanteCentro() {
  int in;   // variable para la entra del sensor de linea
  in = digitalRead (pinsDigitales [3]); // asigna in con el valor que da el sensor
  if (in == 1) {
    return 1; // si el sensor dectecta la linea negra restorna 1
  }
  else {
    return 0;
  }
}


int sensorLineaAdelanteIzquierdo  () {
  int in;   // variable para la entra del sensor de linea
  in = digitalRead (pinsDigitales [5]); // asigna in con el valor que da el sensor
  if (in == 1) {
    return 1; // si el sensor dectecta la linea negra restorna 1
  }
  else {
    return 0;
  }
}



void  motorIzquierdo (String direccion, int velocidad) {
  if ( direccion == "frenado") {
    digitalWrite (pinsDigitales [8], LOW); // pones los dos pines en LOW
    digitalWrite (pinsDigitales [10], LOW);
    analogWrite (pinsDigitales [6], 0);
  }
  else if ( direccion == "atras") {
    digitalWrite (pinsDigitales [8], LOW); // pone el pin 8 alto si la direccion es hacia adelante
    digitalWrite (pinsDigitales [10], HIGH);
    analogWrite (pinsDigitales [6], velocidad);
  }
  else if ( direccion == "adelante") {
    digitalWrite (pinsDigitales [8], HIGH); // pone el pin 10 alto si la direccion es hacia atras
    digitalWrite (pinsDigitales [10], LOW);
    analogWrite (pinsDigitales [6], velocidad);
  }
}

void motorDerecho(String direccion, int velocidad) {
  if ( direccion == "frenado") {
    digitalWrite (pinsDigitales [7], LOW); // pones los dos pines en LOW
    digitalWrite (pinsDigitales [11], LOW);
    analogWrite (pinsDigitales [9], 0);
  }
  else if ( direccion == "atras") {
    digitalWrite (pinsDigitales [7], LOW); // pone el pin 7 alto si la direccion es hacia adelante
    digitalWrite (pinsDigitales [11], HIGH);
    analogWrite (pinsDigitales [9], velocidad);
  }
  else if ( direccion == "adelante") {
    digitalWrite (pinsDigitales [7], HIGH); // pone el pin 11 alto si la direccion es hacia atras
    digitalWrite (pinsDigitales [11], LOW);
    analogWrite (pinsDigitales [9], velocidad);
  }
}

void giroDerecha() {
  int pwmLl = 90;
  motorDerecho ("atras",pwmLl);
  motorIzquierdo ("adelante",pwmLl);
}

void giroDerechaR() {
  motorDerecho ("atras", pwmLG);
  motorIzquierdo ("adelante", pwmLG);
}

void giroizquierda() {
  int pwmLl = 90;
  motorDerecho ("adelante", pwmLl);
  motorIzquierdo ("atras", pwmLl);
}

void giroizquierdaR() {
  motorDerecho ("adelante", pwmLG);
  motorIzquierdo ("atras", pwmLG);
}

void avanzarL() {
  motorDerecho ("adelante",pwmL);
  motorIzquierdo ("adelante",pwmL);
}
void avanzarR() {
  motorIzquierdo ("adelante", pwmR);
  motorDerecho ("adelante", pwmR); 
}

void retroceder() {
  motorDerecho ("atras",pwmR); 
  delay(80);
  motorIzquierdo ("atras",150);  
}


void parar(){
  motorDerecho ("frenado", 0);
  motorIzquierdo ("frenado", 0);
}


int busqueda(){
  int r = 7;
if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =2 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==0){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 1&& sensorLineaAdelanteCentro () == 1&& sensorLineaAdelanteIzquierdo()==1){ r =0 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =1 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =4 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =3 ;}
else if (sensorSharpDerecho() ==0 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =3 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 0&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =3 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==0&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =3 ;}
else if (sensorSharpDerecho() ==1 && sensorSharpCentro() == 1&& sensorSharpIzquierdo () ==1&&   sensorLineaAdelanteDerecho () == 0&& sensorLineaAdelanteCentro () == 0&& sensorLineaAdelanteIzquierdo()==0){ r =3 ;}
return r;
}



int period1 = 2000;
int delayGiro = 150;
int delayRetroceso= 250;
unsigned long time_now = 0;
bool f = true;
bool fr = true;
int c = 2;
void loop() {
c = busqueda();
   Serial.println(c);
    switch (c)
    {    
    case 0:      
      time_now = millis();
      while(millis() < time_now + delayRetroceso){
       retroceder();
       //parar();
       Serial.println("retroceder");
      }
      break;
    case 1:
      giroDerechaR();
      Serial.println("derecha por deteccion");
      break;
    case 2:
    if (f){
      time_now = millis();
      f = false;
    }
    if (millis() > time_now + period1){
      fr = !fr;
      time_now = millis();
    }   
    
    if (fr){
      int ran = random(1, 3);
      if (ran == 1){
        giroDerecha();
      }
      else{
        giroizquierda();
      }
      //parar();
      period1 = 2000;
      Serial.println("derecha defecto");
    }else{
      avanzarL();
      //parar();
      period1 = 500;
       Serial.println("avanzar defecto ----");
    }
      break;
    case 3:
      avanzarL();
      //parar();
      Serial.println("avanzar");
      break;
    case 4:   
      giroizquierdaR();
      delay(100);
      Serial.println("izquierda deteccion");      
      break;
    default:
      parar();
      Serial.println("parar defecto");
      break;
    }

    if (c != 2){
      f = true;
    }

}


/*Serial.println("sharp D, C, I");
  Serial.print(sensorSharpDerecho ());
  Serial.print(",");
  Serial.print(sensorSharpCentro ());
  Serial.print(",");
  Serial.println(sensorSharpIzquierdo ());

  Serial.println("linea");
  Serial.print(sensorLineaAdelanteDerecho ());
  Serial.print(",");
  Serial.print(sensorLineaAdelanteCentro ());
  Serial.print(",");
  Serial.println(sensorLineaAdelanteIzquierdo ()); */