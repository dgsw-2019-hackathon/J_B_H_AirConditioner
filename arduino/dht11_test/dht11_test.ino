#include <Adafruit_Sensor.h>
#include <DHT.h>
#include <DHT_U.h>

#define DHTPIN            3         // Pin which is connected to the DHT sensor.

// Uncomment the type of sensor in use:
#define DHTTYPE           DHT11     // DHT 11 
//#define DHTTYPE           DHT22     // DHT 22 (AM2302)
//#define DHTTYPE           DHT21     // DHT 21 (AM2301)

// See guide for details on sensor wiring and usage:
//   https://learn.adafruit.com/dht/overview

DHT_Unified dht(DHTPIN, DHTTYPE);

uint32_t delayMS;
int led_pin = 13;  // LED핀 번호
int motion_pin = 4; // 인체감지 센서 핀 번호

void setup() {
  Serial.begin(9600); 
  // Initialize device.
  dht.begin();
  pinMode(led_pin, OUTPUT);
  pinMode(motion_pin, INPUT);
  
  sensor_t sensor;
  dht.temperature().getSensor(&sensor);
  
  dht.humidity().getSensor(&sensor);

  // Set delay between sensor readings based on sensor details.
  delayMS = sensor.min_delay / 1000;
}

void loop() {
  // Delay between measurements.
  delay(delayMS+3000);
  // Get temperature event and print its value.
  sensors_event_t event;  
  dht.temperature().getEvent(&event);
  if (isnan(event.temperature)) {
    Serial.println("Error reading temperature!");
  }
  else {
    Serial.println(event.temperature);
  }

  if(digitalRead(motion_pin) == 1)
  {
    digitalWrite(led_pin, HIGH);
    //Serial.println("Motion detected!");
  }
  else
  {
    digitalWrite(led_pin, LOW);
    Serial.println("Motion no");
  }
}
