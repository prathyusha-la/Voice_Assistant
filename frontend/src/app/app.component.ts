import { Component } from '@angular/core';
import { VoiceComponent } from './voice/voice.component'; 
@Component({
  selector: 'app-root',
  standalone: true,
  imports: [VoiceComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'voice-assistant-frontend';
}
