/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,js}",
  ],
  theme: {
    extend: {
        colors:{
            customGreen: '#79AC78',
        },
    },
  },
  plugins: [
    require('daisyui'),
  ],
}

