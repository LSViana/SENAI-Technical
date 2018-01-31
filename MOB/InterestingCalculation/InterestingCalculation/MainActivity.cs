using Android.App;
using Android.Widget;
using Android.OS;
using System;

namespace InterestingCalculation
{
    [Activity(Label = "InterestingCalculation", MainLauncher = true)]
    public class MainActivity : Activity
    {
        public EditText InitialAmount { get; private set; }
        public EditText InterestRate { get; private set; }
        public EditText MonthAmount { get; private set; }
        public EditText FinalAmount { get; private set; }
        public Button ButtonClear { get; private set; }
        public Button ButtonCalculate { get; private set; }

        protected override void OnCreate(Bundle savedInstanceState)
        {
            base.OnCreate(savedInstanceState);

            // Set our view from the "main" layout resource
            SetContentView(Resource.Layout.Main);
            //
            InitialAmount = FindViewById<EditText>(Resource.Id.editTextInitialAmount);
            InterestRate = FindViewById<EditText>(Resource.Id.editTextInterestRate);
            MonthAmount = FindViewById<EditText>(Resource.Id.editTextMonthAmount);
            FinalAmount = FindViewById<EditText>(Resource.Id.editTextFinalAmount);
            ButtonClear = FindViewById<Button>(Resource.Id.buttonClear);
            ButtonCalculate = FindViewById<Button>(Resource.Id.buttonCalculate);
            //
            ButtonClear.Click += ButtonClear_Click;
            ButtonCalculate.Click += ButtonCalculate_Click;
        }

        private void ButtonClear_Click(object sender, System.EventArgs e)
        {
            InitialAmount.Text = string.Empty;
            InterestRate.Text = string.Empty;
            MonthAmount.Text = string.Empty;
            FinalAmount.Text = string.Empty;
            //
            Toast.MakeText(Application.Context, Resource.String.fields_cleared, ToastLength.Long).Show();
        }

        private void ButtonCalculate_Click(object sender, System.EventArgs e)
        {
            // Values to Calculate
            Double initialAmount = 0, interestRate = 0;
            Int32 monthAmount = 0;
            // Texts from View
            var initialAmountStr = InitialAmount.Text;
            var interestRateStr = InterestRate.Text;
            var monthAmountStr = MonthAmount.Text;
            //
            var errorMessage = String.Empty;
            if(!Double.TryParse(initialAmountStr, out initialAmount))
            {
                if (errorMessage.Length != 0)
                    errorMessage += "\n";
                errorMessage += "Fill the field Initial Amount correctly!";
            }
            if (!Double.TryParse(interestRateStr, out interestRate))
            {
                if (errorMessage.Length != 0)
                    errorMessage += "\n";
                errorMessage += "Fill the field Interest Rate correctly!";
            }
            if (!Int32.TryParse(monthAmountStr, out monthAmount))
            {
                if (errorMessage.Length != 0)
                    errorMessage += "\n";
                errorMessage += "Fill the field Month Amount correctly!";
            }
            //
            if(String.IsNullOrWhiteSpace(errorMessage))
            {
                var finalAmount = initialAmount + (interestRate / 100 * initialAmount * monthAmount);
                FinalAmount.Text = finalAmount.ToString();
            }
            else
            {
                // Something is Wrong
                Toast.MakeText(Application.Context, errorMessage, ToastLength.Long).Show();
            }
        }
    }
}

