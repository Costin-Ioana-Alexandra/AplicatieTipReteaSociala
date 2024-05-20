import { Injectable } from '@angular/core';
import { Message } from './message.model'; // Importăm clasa Message

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  currentUserMessages: Message[] = [];
  otherUserMessages: Message[] = [];
}
