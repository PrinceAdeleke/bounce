const axios = require('axios');

const accountId = '00000003-0000-1000-8000-00805f9b34fb';
const URL = `http://localhost:8080/api/account/${accountId}/location`;

const ping = async ({ baseUrl, accountId, transmitterId }, { latitude, longitude }) => {
  const gpsTimestamp = new Date().toISOString();
  const payload = { transmitterId, accountId, latitude, longitude, gpsTimestamp };

  try {
    await axios.post(`http://localhost:8080/api/account/${accountId}/location`, payload);
      return true;
  } catch(error) {
    console.log(error);
  }
};

module.exports = ping;
