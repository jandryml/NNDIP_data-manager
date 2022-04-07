import { LitElement, html, css, customElement } from 'lit-element';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/horizontal-layout/src/vaadin-horizontal-layout.js';
import '@vaadin/text-field/src/vaadin-text-field.js';
import '@vaadin/combo-box/src/vaadin-combo-box.js';
import '@vaadin/checkbox/src/vaadin-checkbox.js';

@customElement('limit-plan-view')
export class LimitPlanView extends LitElement {
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
<vaadin-vertical-layout style="width: 100%; height: 100%; align-items: center; margin: var(--lumo-space-m);" theme="spacing-s">
 <h2>Temperature config </h2>
 <vaadin-horizontal-layout style="align-self: flex-start; flex-wrap: wrap;" theme="spacing-xl">
  <vaadin-horizontal-layout style="flex-wrap: wrap; align-self: flex-end;" theme="spacing">
   <vaadin-text-field type="text" tabindex="" label="Optimal value"></vaadin-text-field>
   <vaadin-text-field type="text" tabindex="" label="Tolerance"></vaadin-text-field>
  </vaadin-horizontal-layout>
  <vaadin-vertical-layout style="align-self: flex-end; flex-wrap: wrap;">
   <h4>Max threshold violation event </h4>
   <vaadin-horizontal-layout style="flex-wrap: wrap;" theme="spacing">
    <vaadin-combo-box tabindex="" label="Event"></vaadin-combo-box>
   </vaadin-horizontal-layout>
  </vaadin-vertical-layout>
  <vaadin-vertical-layout style="align-self: flex-end;">
   <h4>Min threshold violation event</h4>
   <vaadin-horizontal-layout style="flex-wrap: wrap;" theme="spacing">
    <vaadin-combo-box tabindex="" label="Event"></vaadin-combo-box>
   </vaadin-horizontal-layout>
  </vaadin-vertical-layout>
  <vaadin-checkbox style="align-self: flex-end;" tabindex="" label="Enabled" type="checkbox" value="on"></vaadin-checkbox>
 </vaadin-horizontal-layout>
 <h2>Co2 config</h2>
 <vaadin-vertical-layout style="align-self: flex-start; flex-wrap: wrap;">
  <vaadin-horizontal-layout style="align-self: flex-start; flex-wrap: wrap;" theme="spacing-xl">
   <vaadin-horizontal-layout style="flex-wrap: wrap;" theme="spacing">
    <vaadin-text-field type="text" tabindex="" label="Limit value" style="align-self: flex-end;"></vaadin-text-field>
    <vaadin-text-field type="text" tabindex="" label="Optimal value" style="align-self: flex-end;"></vaadin-text-field>
   </vaadin-horizontal-layout>
   <vaadin-vertical-layout style="align-self: flex-end; flex-wrap: wrap;">
    <vaadin-horizontal-layout style="flex-wrap: wrap;" theme="spacing">
     <vaadin-combo-box tabindex="" label="Event"></vaadin-combo-box>
    </vaadin-horizontal-layout>
   </vaadin-vertical-layout>
   <vaadin-checkbox style="align-self: flex-end;" tabindex="" label="Enabled" type="checkbox" value="on"></vaadin-checkbox>
  </vaadin-horizontal-layout>
 </vaadin-vertical-layout>
</vaadin-vertical-layout>
`;
  }

  // Remove this method to render the contents of this view inside Shadow DOM
  createRenderRoot() {
    return this;
  }
}
