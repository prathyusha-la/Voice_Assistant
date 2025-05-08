import { Component } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-voice',
  standalone: true, // 
  imports: [CommonModule],
  templateUrl: './voice.component.html',
  styleUrls: ['./voice.component.css']
})
export class VoiceComponent {
  mediaRecorder: any;
  audioChunks: any[] = [];
  isRecording = false;
  response = '';

  constructor(private http: HttpClient) {}

  startRecording(): void {
    this.audioChunks = [];
    this.isRecording = true;

    navigator.mediaDevices.getUserMedia({ audio: true }).then(stream => {
      this.mediaRecorder = new MediaRecorder(stream);
      this.mediaRecorder.start();

      this.mediaRecorder.ondataavailable = (event: any) => {
        this.audioChunks.push(event.data);
      };
    });
  }

  stopRecording(): void {
    this.isRecording = false;
    this.mediaRecorder.stop();

    this.mediaRecorder.onstop = () => {
      const audioBlob = new Blob(this.audioChunks, { type: 'audio/wav' });
      const formData = new FormData();
      formData.append('file', audioBlob, 'voice.wav');

      this.http.post('http://localhost:9090/api/voice/transcribe', formData, { responseType: 'text' })
        .subscribe({
          next: res => this.response = res,
          error: () => this.response = 'Something went wrong!'
        });
    };
  }
}
