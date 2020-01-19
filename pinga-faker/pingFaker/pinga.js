const ping = require('./ping');

const getRandomNumber = (max) => {
  return Math.floor(Math.random() * Math.floor(max));
};

const pinga = async (config, locations) => {
  const location = locations[getRandomNumber(locations.length)];
  const response = await ping(config, location);
  if (response) {
    console.log('location ping: ', location);
    setTimeout(async () => {
      return await pinga(config, locations);
    }, 1000);
  } else {
    console.log('failed to ping location... retrying');
    setTimeout(async () => {
      return await pinga(config, locations);
    }, 1000);
  }
};

module.exports = { start: pinga };
