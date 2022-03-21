import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/form-layout/src/vaadin-form-layout.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/checkbox/src/vaadin-checkbox.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/button/src/vaadin-button.js';

@customElement('limit-config-view')
export class LimitConfigView extends LitElement {
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
<vaadin-form-layout>
 <vaadin-vertical-layout theme="spacing" style="align-items: center; flex: 3;">
  <vaadin-vertical-layout theme="spacing">
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Max temperature config </label>
   </vaadin-horizontal-layout>
   <vaadin-checkbox style="align-self: center;" tabindex="" type="checkbox" value="on">
     Enable 
   </vaadin-checkbox>
   <vaadin-text-field label="Threshold" style="align-self: center;" type="text" tabindex="" value="25.5"></vaadin-text-field>
   <vaadin-text-field label="Tolerance" style="align-self: center;" type="text" tabindex="" value="23"></vaadin-text-field>
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Action </label>
   </vaadin-horizontal-layout>
   <vaadin-combo-box style="align-self: center;" tabindex=""></vaadin-combo-box>
   <vaadin-button style="align-self: center;">
     Add action 
   </vaadin-button>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout theme="spacing">
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Min temperature config</label>
   </vaadin-horizontal-layout>
   <vaadin-checkbox style="align-self: center;" tabindex="" type="checkbox" value="on" checked>
     Enable 
   </vaadin-checkbox>
   <vaadin-text-field label="Threshold" style="align-self: center;" type="text" tabindex="" value="20.5"></vaadin-text-field>
   <vaadin-text-field label="Tolerance" style="align-self: center;" type="text" tabindex="" value="23"></vaadin-text-field>
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Action </label>
   </vaadin-horizontal-layout>
   <vaadin-combo-box style="align-self: center;" tabindex=""></vaadin-combo-box>
   <vaadin-button style="align-self: center;">
     Add action 
   </vaadin-button>
  </vaadin-vertical-layout>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing" style="flex: 3; align-items: center;">
  <vaadin-vertical-layout theme="spacing">
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Max humidity config</label>
   </vaadin-horizontal-layout>
   <vaadin-checkbox style="align-self: center;" tabindex="" type="checkbox" value="on" checked>
     Enable 
   </vaadin-checkbox>
   <vaadin-text-field label="Threshold" style="align-self: center;" type="text" tabindex="" value="55"></vaadin-text-field>
   <vaadin-text-field label="Tolerance" style="align-self: center;" type="text" tabindex="" value="35"></vaadin-text-field>
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Action </label>
   </vaadin-horizontal-layout>
   <vaadin-combo-box style="align-self: center;" tabindex=""></vaadin-combo-box>
   <vaadin-button style="align-self: center;">
     Add action 
   </vaadin-button>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout theme="spacing">
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Min humidity config</label>
   </vaadin-horizontal-layout>
   <vaadin-checkbox style="align-self: center;" tabindex="" type="checkbox" value="on">
     Enable 
   </vaadin-checkbox>
   <vaadin-text-field label="Threshold" style="align-self: center;" type="text" tabindex="" value="25"></vaadin-text-field>
   <vaadin-text-field label="Tolerance" style="align-self: center;" type="text" tabindex="" value="35"></vaadin-text-field>
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Action </label>
   </vaadin-horizontal-layout>
   <vaadin-combo-box style="align-self: center;" tabindex=""></vaadin-combo-box>
   <vaadin-button style="align-self: center;">
     Add action 
   </vaadin-button>
  </vaadin-vertical-layout>
 </vaadin-vertical-layout>
 <vaadin-vertical-layout theme="spacing" style="flex: 3; align-items: center;">
  <vaadin-vertical-layout theme="spacing" style="align-items: flex-start;">
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Max Co2 config</label>
   </vaadin-horizontal-layout>
   <vaadin-checkbox style="align-self: center;" tabindex="" type="checkbox" value="on" checked>
     Enable 
   </vaadin-checkbox>
   <vaadin-text-field label="Threshold" style="align-self: center;" type="text" tabindex="" value="900"></vaadin-text-field>
   <vaadin-text-field label="Tolerance" style="align-self: center;" type="text" tabindex="" value="600"></vaadin-text-field>
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Action </label>
   </vaadin-horizontal-layout>
   <vaadin-combo-box style="align-self: center;" tabindex=""></vaadin-combo-box>
   <vaadin-button style="align-self: center;">
     Add action 
   </vaadin-button>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout theme="spacing" style="align-items: flex-start;">
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Min Co2 config</label>
   </vaadin-horizontal-layout>
   <vaadin-checkbox style="align-self: center;" tabindex="" type="checkbox" value="on">
     Enable 
   </vaadin-checkbox>
   <vaadin-text-field label="Threshold" style="align-self: center;" type="text" tabindex="" value="200"></vaadin-text-field>
   <vaadin-text-field label="Tolerance" style="align-self: center;" type="text" tabindex="" value="400"></vaadin-text-field>
   <vaadin-horizontal-layout theme="spacing" style="align-self: center;">
    <label>Action </label>
   </vaadin-horizontal-layout>
   <vaadin-combo-box style="align-self: center;" tabindex=""></vaadin-combo-box>
   <vaadin-button style="align-self: center;">
     Add action 
   </vaadin-button>
  </vaadin-vertical-layout>
 </vaadin-vertical-layout>
</vaadin-form-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
