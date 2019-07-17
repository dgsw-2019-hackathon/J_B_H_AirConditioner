#include <DHT.h>
#define DHTPIN 5   
#define DHTTYPE DHT11   // DHT 22  (AM2302), AM2321

DHT dht(DHTPIN, DHTTYPE);
int serialread = 0;
void setup() {
  Serial.begin(9600);
  dht.begin();
  pinMode(8, OUTPUT);
}

void loop() {
  if(Serial.available()){
    digitalWrite(8, Serial.read());
  }
  else{
    float h = dht.readHumidity();
    float t = dht.readTemperature();
    if (isnan(h) || isnan(t)) {
      Serial.println(F("Failed to read from DHT sensor!"));
      return;
    }
    Serial.println(String(t)+"/"+String(h));
  }
  delay(1000);
}
