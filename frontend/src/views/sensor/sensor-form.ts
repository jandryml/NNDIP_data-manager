import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/button/src/vaadin-button.js';
import '@vaadin/text-field/src/vaadin-text-field.js';

@customElement('sensor-form')
export class SensorForm extends LitElement {
  static get styles() {
    return css`
      :host {
          display: block;
          height: 100%;
      }
      `;
  }

  render() {
    return html`
<vaadin-vertical-layout theme="spacing" style="width: 100%; height: 100%;">
 <vaadin-horizontal-layout theme="spacing" style="flex-wrap: wrap; flex-grow: 0; flex-shrink: 1; align-self: center; justify-content: center;">
  <vaadin-text-field label="Sensor ID" id="id" type="text" readonly tabindex="" style="flex:1; flex-grow: 0;"></vaadin-text-field>
  <vaadin-text-field label="Name" id="name" type="text" required tabindex="" style="flex:1; flex-grow: 0;"></vaadin-text-field>
  <vaadin-combo-box id="sensorType" autoselect tabindex="" label="Sensor type" autofocus style="flex:1; flex-grow: 0; flex-shrink: 1;"></vaadin-combo-box>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout theme="spacing" style="flex-grow: 0; flex-wrap: wrap; align-content: flex-start; align-self: center;">
  <vaadin-button theme="primary" id="save">
    Save 
  </vaadin-button>
  <vaadin-button theme="error" id="delete">
    Delete 
  </vaadin-button>
  <vaadin-button theme="tertiary" id="close">
    Close 
  </vaadin-button>
 </vaadin-horizontal-layout>
 <span></span>
 <span></span>
 <h4 style="align-self: center;">Latest values</h4>
 <vaadin-horizontal-layout theme="spacing" style="align-self: center; justify-content: center; flex-wrap: wrap;">
  <vaadin-text-field label="Time" id="timestampTextField" type="text" readonly tabindex=""></vaadin-text-field>
  <vaadin-text-field label="Hits" type="text" tabindex="" id="hitsTextField" readonly></vaadin-text-field>
 </vaadin-horizontal-layout>
 <vaadin-horizontal-layout theme="spacing" style="flex-wrap: wrap; align-self: center; justify-content: center;">
  <vaadin-text-field label="Temperature" type="text" tabindex="" id="temperatureTextField" readonly></vaadin-text-field>
  <vaadin-text-field label="Humidity" type="text" tabindex="" id="humidityTextField" readonly></vaadin-text-field>
  <vaadin-text-field label="Co2" type="text" tabindex="" id="co2TextField" readonly></vaadin-text-field>
 </vaadin-horizontal-layout>
</vaadin-vertical-layout>
`;
  }

}
