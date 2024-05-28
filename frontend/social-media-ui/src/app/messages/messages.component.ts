import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MessageService } from './message.service';
import { Message } from './message.model';
import { FormsModule } from '@angular/forms';
import { FileService } from './file.service';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-messages',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, RouterModule],
  templateUrl: './messages.component.html',
  styleUrl: './messages.component.css',
})
export class MessagesComponent {
  newMessageContent: string = '';

  constructor(
    public messageService: MessageService,
    private fileService: FileService
  ) { }

  ngOnInit() {
    this.loadMessagesFromFile();
  }

  loadMessagesFromFile() {
    this.fileService.loadMessagesFromFile('assets/mesaje.txt').subscribe(
      data => {
        const messages = data.split('\n');
        messages.forEach(message => {
          if (message.trim() !== '') {
            this.messageService.currentUserMessages.push(new Message(message, 'current'));
          }
        });
      },
      error => {
        console.error('Could not load messages:', error);
      }
    );
  }

  sendMessage() {
    if (this.newMessageContent.trim() !== '') {

      if(this.newMessageContent === ':)') {
        this.newMessageContent = '😀';
      }
      else if(this.newMessageContent === ':(') {
        this.newMessageContent = '☹️';
      }
      else if(this.newMessageContent === '<3') {
        this.newMessageContent = '🤍';
      }

      const newMessage = new Message(this.newMessageContent, 'current');
      this.messageService.currentUserMessages.push(newMessage);
      this.newMessageContent = '';
    }
  }


  generateWelcomeMessage(): string {
    return `Space Chat!`;
    }
}
