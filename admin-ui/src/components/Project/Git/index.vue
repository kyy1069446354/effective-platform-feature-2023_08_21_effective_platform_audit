<template>
  <div>
    <svg-icon icon-class="github" @click="goto" />
  </div>
</template>

<script setup>
const url = ref('https://github.com/tangyang/山东邮政-Back-End');

function goto() {
  window.open(url.value);
}
</script>
