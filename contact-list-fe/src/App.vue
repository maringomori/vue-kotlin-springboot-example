<script>
import { ref } from "vue";
import Header from "./components/Header.vue";
import ContactList from "./components/ContactList.vue";
import { getAllContacts } from "./api/contacts";
import { useToast } from "vue-toastification";

export default {
  components: { Header, ContactList },
  setup() {
    const toast = useToast();
    return { toast };
  },
  data() {
    return { contacts: [] };
  },
  methods: {
    async getContacts() {
      getAllContacts()
        .then((response) => {
          this.contacts = response.data;
        })
        .catch((error) => {
          this.toast.error("Can't reach the API");
        });
    },
  },
  async mounted() {
    await this.getContacts();
  },
};
</script>

<template>
  <main id="main">
    <Header @refresh-contact="() => getContacts()" />
    <div class="container">
      <ContactList
        :contacts="contacts"
        @refresh-contact="() => getContacts()"
      />
    </div>
  </main>
</template>

<style scoped>
main {
  width: 100%;
  height: 100%;
}
.container {
  padding-top: 12rem;
  width: 53%;
  margin: 0 auto;
  min-height: 100vh;
  border-right: 1px solid var(--primary-60);
  border-left: 1px solid var(--primary-60);
  padding-bottom: 12rem;
}
@media screen and (max-width: 826px) {
  .container {
    width: 90%;
  }
}
</style>
