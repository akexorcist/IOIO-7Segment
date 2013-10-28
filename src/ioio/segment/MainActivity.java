// Use with Cathode 7-Segment

package ioio.segment;

import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;
import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

/*
 * 
 * Data Port
 ***************************************************************************************
 * 7-Segment  | Port 1 | Port 2 | Port 3 | Port 4 | Port 5 | Port 6 | Port 7 | Port 8  | 
 ***************************************************************************************
 * IOIO Board | Port A | Port B | Port C | Port D | Port E | Port F | Port G | Port Dp |
 ***************************************************************************************
 * 
 * Digit Port 
 ******************************************************
 * 7-Segment  | Port 11 | Port 12 | Port 13 | Port 14 |
 ******************************************************
 * IOIO Board | Digit 1 | Digit 2 | Digit 3 | Digit 4 |
 ******************************************************
 *
 */

public class MainActivity extends IOIOActivity {
	
	// Create object for button widget
	Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnD,btnS;

	// Create object for toggle button widget
	ToggleButton btnDigit1, btnDigit2, btnDigit3, btnDigit4;
	
	// Create num variable to contain hex code of number for 7-segment
	int num[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D, 0x7D, 0x07, 0x7F, 0x6F, 0x80, 0x00};
	
	// Create buffer variable to contain number each of the digit
	int buffer[] = {0, 0, 0, 0};
	
	// Create digit variable to contain number of digit
	// 0 = Digit 1 , 1 = Digit 2 , 2 = Digit 3 , 3 = Digit 4
	int digit = 0;
	
	// Create variable for set number of hex code to be read from num 
	static int count = 0x01;
	
	// onCreate function that will be do first when application is startup
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set format of image that will be use in this application
        getWindow().setFormat(PixelFormat.RGBA_8888);

		// Run application in no title bar
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        // Set application use layout from main.xml
		setContentView(R.layout.main);
		
		// Assigne object to button widget 
        btnDigit1 = (ToggleButton) findViewById(R.id.btnDigit1);
        btnDigit1.setChecked(true);
        btnDigit2 = (ToggleButton) findViewById(R.id.btnDigit2);
		btnDigit3 = (ToggleButton) findViewById(R.id.btnDigit3);
		btnDigit4 = (ToggleButton) findViewById(R.id.btnDigit4);
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);
		btn6 = (Button) findViewById(R.id.btn6);
		btn7 = (Button) findViewById(R.id.btn7);
		btn8 = (Button) findViewById(R.id.btn8);
		btn9 = (Button) findViewById(R.id.btn9);
		btn0 = (Button) findViewById(R.id.btn0);
		btnD = (Button) findViewById(R.id.btnD);
		btnS = (Button) findViewById(R.id.btnS);

		// Create event when user selected these button widget
        btnDigit1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Set digit value to 1 (Set digit 1)
				digit = 0;
				
				// Set state of all digit button  
				// This function is btnDigit1 
				// then btnDigit1 set to on 
				// and other buttom set to off
				btnDigit1.setChecked(true);
				btnDigit2.setChecked(false);
				btnDigit3.setChecked(false);
				btnDigit4.setChecked(false);
			}
		});
	
        btnDigit2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				digit = 1;
				btnDigit1.setChecked(false);
				btnDigit2.setChecked(true);
				btnDigit3.setChecked(false);
				btnDigit4.setChecked(false);
			}
		});
        
		btnDigit3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				digit = 2;
				btnDigit1.setChecked(false);
				btnDigit2.setChecked(false);
				btnDigit3.setChecked(true);
				btnDigit4.setChecked(false);
			}
		});
		
		btnDigit4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				digit = 3;
				btnDigit1.setChecked(false);
				btnDigit2.setChecked(false);
				btnDigit3.setChecked(false);
				btnDigit4.setChecked(true);
			}
		});
		
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Set variable equal 1 
				buffer[digit] = 1;
			}
		});
		
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 2;
			}
		});
		
		btn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 3;
			}
		});
		
		btn4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 4;
			}
		});
		
		btn5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 5;
			}
		});
		
		btn6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 6;
			}
		});
		
		btn7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 7;
			}
		});
		
		btn8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 8;
			}
		});
		
		btn9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 9;
			}
		});
		
		btn0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 0;
			}
		});
		
		btnD.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 10;
			}
		});
		
		btnS.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				buffer[digit] = 11;
			}
		});
	}

	// onResume function
	@Override
	public void onResume() {
		super.onResume();
	}
	
	// onPause function
	public void onPause(){
		super.onPause();
	}
	
	// onDestroy function
	public void onDestroy(){
		super.onDestroy();
	}

	// This class is thread for ioio board
	// You can control ioio board through this class 
	class Looper extends BaseIOIOLooper {

		// Create object for assigned to output port 
		DigitalOutput Pa, Pb, Pc, Pd, Pe, Pf, Pg, Pdp, D1, D2, D3, D4;
		
		// This function will do when application is startup 
		// Like onCreate function but use with ioio board
		@Override
		public void setup() throws ConnectionLostException {
			
			// Assigned eacth object to each output port and initial state is false
			Pa = ioio_.openDigitalOutput(1, false);
			Pb = ioio_.openDigitalOutput(2, false);
			Pc = ioio_.openDigitalOutput(3, false);
			Pd = ioio_.openDigitalOutput(4, false);
			Pe = ioio_.openDigitalOutput(5, false);
			Pf = ioio_.openDigitalOutput(6, false);
			Pg = ioio_.openDigitalOutput(7, false);
			Pdp = ioio_.openDigitalOutput(8, false);
			D1 = ioio_.openDigitalOutput(12, false);
			D2 = ioio_.openDigitalOutput(13, false);
			D3 = ioio_.openDigitalOutput(14, false);
			D4 = ioio_.openDigitalOutput(15, false);
			
			// if we use any command which not ioio command 
			// in any ioio board's function program will force close
			// then we could use runnable to avoid force close
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					// When device connected with ioio board 
					// Toast will show "Connected!"
					Toast.makeText(getApplicationContext(), "Connected!", Toast.LENGTH_SHORT).show();
				}		
			});
		}

		// This function will always running when device connect with ioio board
		// It use for control ioio board
		@Override
		public void loop() throws ConnectionLostException {
			try {
				// Set boolean logic to each of digit port and data port
				// and delay 6 ms before change to next digit
				// Digit 1
				D4.write(false);
				Pa.write(check(num[buffer[0]]));
				Pb.write(check(num[buffer[0]] >> 1));
				Pc.write(check(num[buffer[0]] >> 2));
				Pd.write(check(num[buffer[0]] >> 3));
				Pe.write(check(num[buffer[0]] >> 4));
				Pf.write(check(num[buffer[0]] >> 5));
				Pg.write(check(num[buffer[0]] >> 6));
				Pdp.write(check(num[buffer[0]] >> 7));
				D1.write(true);
				Thread.sleep(6);
						
				// Digit 2
				D1.write(false);
				Pa.write(check(num[buffer[1]]));
				Pb.write(check(num[buffer[1]] >> 1));
				Pc.write(check(num[buffer[1]] >> 2));
				Pd.write(check(num[buffer[1]] >> 3));
				Pe.write(check(num[buffer[1]] >> 4));
				Pf.write(check(num[buffer[1]] >> 5));
				Pg.write(check(num[buffer[1]] >> 6));
				Pdp.write(check(num[buffer[1]] >> 7));
				D2.write(true);
				Thread.sleep(6);
						
				// Digit 3
				D2.write(false);
				Pa.write(check(num[buffer[2]]));
				Pb.write(check(num[buffer[2]] >> 1));
				Pc.write(check(num[buffer[2]] >> 2));
				Pd.write(check(num[buffer[2]] >> 3));
				Pe.write(check(num[buffer[2]] >> 4));
				Pf.write(check(num[buffer[2]] >> 5));
				Pg.write(check(num[buffer[2]] >> 6));
				Pdp.write(check(num[buffer[2]] >> 7));
				D3.write(true);
				Thread.sleep(6);
					
				// Digit 4
				D3.write(false);
				Pa.write(check(num[buffer[3]]));
				Pb.write(check(num[buffer[3]] >> 1));
				Pc.write(check(num[buffer[3]] >> 2));
				Pd.write(check(num[buffer[3]] >> 3));
				Pe.write(check(num[buffer[3]] >> 4));
				Pf.write(check(num[buffer[3]] >> 5));
				Pg.write(check(num[buffer[3]] >> 6));
				Pdp.write(check(num[buffer[3]] >> 7));
				D4.write(true);
				Thread.sleep(6);
			} catch (InterruptedException e) { }
		}
		
		// Function for convert interger value to boolean
		public boolean check(int i) {
			
			// Create variable for convert binary to boolean
			// Use for command LED on IOIO Board
			boolean st = false;
			
			// Use only lowest bit
			i = i & 0x01;
			
			// If i = 0 set st = false or if i =1 set st = true
			// and return st back to main program
			if(i == 0x00)
				st = false;
			else if(i == 0x01)
				st = true;
			return st;
		}
	}
	
	@Override
    protected IOIOLooper createIOIOLooper() {
        return new Looper();
    }
}