const csvReader = require('./csv/csvReader');
const csvParser = require('./csv/csvParser');
const pinga = require('./pingFaker/pinga');
const config = require('./config/config-dev');

const run = async (config) => {
  console.log('CONFIG => ', config);
  console.log('Loading test data...');
  const csvData = await csvReader.load('./data/latlng.csv');

  console.log('Converting test data to location objects...');
  const locations = csvParser.parseCsv(csvData, (row) => ({
    latitude: row[0],
    longitude: row[1]
  }));

  console.log(`Successfully loaded ${locations.length} locations`);
  console.log('Start pinga...');
  pinga.start(config, locations);
};

run(config);
