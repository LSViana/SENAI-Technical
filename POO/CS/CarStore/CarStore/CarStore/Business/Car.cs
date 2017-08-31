using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarStore.Business
{
    class Car
    {
        public Car()
        {
            // Standard Constructor
        }
        public Car(String Model, int MaxSpeed, Color Color, double ZeroToHundred, Engine Engine)
        {
            this.Model = Model;
            this.MaxSpeed = MaxSpeed;
            this.Color = Color;
            this.ZeroToHundred = ZeroToHundred;
            this.Engine = Engine;
        }
        public override string ToString()
        {
            return String.Format("Model: {0} - Max Speed: {1}km/h - Color: {2} - Zero to hundred: {3:F2} seconds - Type of Engine: {4} - Engine's Potency: {5} horses", Model, MaxSpeed, Color, ZeroToHundred, ((Engine != null) ? Engine.Type.ToString() : "[No Engine]"), Engine != null ? Engine.Potency : 0);
        }

        public string Model { get; set; }
        public int MaxSpeed { get; set; }
        public Color Color { get; set; }
        public double ZeroToHundred { get; set; }
        public Engine Engine { get; set; }
    }
}
