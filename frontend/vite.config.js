import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      // '/api/v1/computer' 로 시작하는 요청을
      // 'http://223.130.151.122:8080' 으로 포워딩
      "/api/v1/computer": {
        target: "http://223.130.151.122:8080",
        changeOrigin: true, //이부분 수정함. 김학수
      },
    },
  },
})
