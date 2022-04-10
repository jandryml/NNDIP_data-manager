import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/vaadin-ordered-layout/src/vaadin-vertical-layout.js';
import './settings-sensors-config';
import './setting-device-config';

@customElement('settings-view')
export class SettingsView extends LitElement {
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
<vaadin-vertical-layout style="align-items: flex-start;">
 <settings-sensors-config style="width: 100%;" id="settingsSensorsConfig"></settings-sensors-config>
 <setting-device-config id="settingDeviceConfig" style="width: 100%; height: 100%;"></setting-device-config>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
