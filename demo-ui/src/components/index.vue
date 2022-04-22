<template>
  <div>
    <textarea v-model="code"></textarea>
    <button @click="runCode">运行</button>
    <textarea v-model="reasult"></textarea>
    <a href="/runCode/runCode">跳转</a>
  </div>
</template>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>

<script>
const axios = require("axios");
export default {
  data() {
    return {
      code: "",
      type: "",
      reasult: ""
    };
  },
  methods: {
    runCode() {
      axios({
        method: "post",
        url: "http://172.4.56.177:8080/code/test",
        params: { code: this.code },
        // headers: { "Content-Type": "application/x-www-form-urlencoded" },
      }).then((res) => {
          this.reasult = res.data;
      });
    },
  },
};
</script>
