import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/api/v1/computer": {
        target: "http://223.130.151.122:8080",
        changeOrigin: true,
      },
    },
  },
})
