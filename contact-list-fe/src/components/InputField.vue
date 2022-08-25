<script>
export default {
  name: "InputField",
  props: ["label", "placeholder", "modelValue"],
};
</script>
<template>
  <div class="input">
    <label class="message label"> {{ label }} </label>
    <input
      @input="this.$emit('update:modelValue', $event.target.value)"
      class="input-field"
      :value="modelValue"
      type="text"
      :placeholder="placeholder"
    />
  </div>
</template>
<style scoped>
.input {
  display: flex;
  flex-direction: column;
}
.label {
  color: var(--text-secondary);
  margin: 1rem 0 0.25rem 0;
}
.input-field {
  padding: 0.75rem;
  background: var(--primary-80);
  border: 1px solid var(--primary-60);
  border-radius: 8px;
  color: var(--text-primary);
  font-family: "LexenDenca";
}

.input-field:hover {
  background: var(--primary-80);
  border: 1px solid var(--primary-30);
}

.input-field:focus {
  outline: none;
  background: var(--primary-60);
  border: 1px solid var(--primary-10);
}
.input-field::placeholder {
  color: rgba(255, 255, 255, 0.32);
}
</style>
