<template>
  <div>
    <textarea v-model="code"></textarea>
    <button @click="compileCode">编译</button>
    <button @click="runCode">运行</button>
    <textarea v-model="reasult"></textarea>
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
      reasult: "",
    };
  },
  methods: {
    compileCode() {
      axios({
        method: "post",
        url: "http://172.4.56.177:8080/code/compileCode",
        params: { code: this.code },
        // headers: { "Content-Type": "application/x-www-form-urlencoded" },
      }).then((res) => {
        this.reasult = res.data;
      });
    },
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
