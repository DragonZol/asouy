<template>
  <div class="support-chat">
    <h3 class="chat-title">Чат поддержки</h3>
    <div class="chat-window">
      <div class="messages">
        <div v-for="(msg, index) in messages" :key="index" class="message">
          <span class="time">{{ formatTime(msg.timestamp) }}</span>
          <span class="text">{{ msg.text }}</span>
        </div>
      </div>
      <div class="chat-input">
        <input
          type="text"
          v-model="newMessage"
          @keyup.enter="sendMessage"
          placeholder="Введите сообщение"
        />
        <button @click="sendMessage">Отправить</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ApplicationSupportChat',
  data() {
    return {
      messages: [],
      newMessage: ''
    }
  },
  mounted() {
    // Load persisted chat history
    const stored = localStorage.getItem('supportChatMessages');
    if (stored) {
      try {
        this.messages = JSON.parse(stored);
      } catch (e) {
        console.warn('Не удалось распарсить историю чата:', e);
      }
    }
  },
  methods: {
    sendMessage() {
      const text = this.newMessage.trim();
      if (!text) return;

      const msg = { text, timestamp: new Date().toISOString() };
      this.messages.push(msg);
      this.newMessage = '';

      // Persist immediately after adding
      this.saveMessages();
      
      // Scroll to bottom
      this.$nextTick(() => {
        const container = this.$el.querySelector('.messages');
        container.scrollTop = container.scrollHeight;
      });
    },
    formatTime(date) {
      return new Date(date).toLocaleTimeString();
    },
    saveMessages() {
      try {
        localStorage.setItem('supportChatMessages', JSON.stringify(this.messages));
      } catch (e) {
        console.error('Ошибка сохранения чата в localStorage:', e);
      }
    }
  },
  watch: {
    // Persist on external changes too
    messages: {
      handler() {
        this.saveMessages();
      },
      deep: true
    }
  }
}
</script>

<style scoped>
.support-chat {
  /* Пусть весь блок растягивается на всю доступную высоту */
  flex: 1;
  display: flex;
  flex-direction: column;

  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 15px;
  background-color: #fff;
  color: #333; /* Текст в светлой теме */
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.chat-title {
  margin-bottom: 10px;
  font-size: 1.25rem;
  border-bottom: 2px solid #ddd;
  padding-bottom: 5px;
}

.chat-window {
  /* Располагаем содержимое вертикально, растягиваем */
  flex: 1;
  display: flex;
  flex-direction: column;
}

.messages {
  flex: 1;
  overflow-y: auto;
  margin-bottom: 10px;
}

.message {
  padding: 5px;
  border-bottom: 1px solid #eee;
}

.time {
  font-size: 0.8rem;
  color: #888;
  margin-right: 8px;
}

.chat-input {
  display: flex;
}

.chat-input input {
  flex: 1;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.chat-input button {
  margin-left: 10px;
  padding: 8px 12px;
  background-color: #4caf50;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
.chat-input button:hover {
  background-color: #45a045;
}

/* ====================== */
/* Тёмная тема для чата  */
/* ====================== */

/* Если в вашем приложении для тёмной темы используется класс .my-app-dark
   на одном из родительских контейнеров, то можно переопределить стили так: */
.my-app-dark .support-chat {
  background-color: var(--dark-bg, #1e1e1e);
  color: var(--dark-text, #e0e0e0);
  border: 1px solid #444;
}

/* Заголовок чата в тёмном режиме */
.my-app-dark .support-chat .chat-title {
  border-bottom: 2px solid #444;
}

/* Сообщения */
.my-app-dark .support-chat .message {
  border-bottom: 1px solid #333;
}

/* Ввод и кнопка в тёмном режиме */
.my-app-dark .support-chat .chat-input input {
  background-color: #333;
  color: #ddd;
  border: 1px solid #555;
}

.my-app-dark .support-chat .chat-input button {
  background-color: #66bb6a; /* Можно использовать другую заливку в темноте */
}
.my-app-dark .support-chat .chat-input button:hover {
  background-color: #5aaa5b;
}
</style>
